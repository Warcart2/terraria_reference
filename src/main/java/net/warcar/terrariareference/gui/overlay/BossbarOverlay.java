
package net.warcar.terrariareference.gui.overlay;

import net.warcar.terrariareference.procedures.IfBossProcedure;
import net.warcar.terrariareference.procedures.BossbarValueProcedure;
import net.warcar.terrariareference.procedures.BossbarLogicProcedure;
import net.warcar.terrariareference.BossEntity;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.Entity;
import net.warcar.terrariareference.procedures.CurBossProcedure;
import net.minecraftforge.registries.ForgeRegistries;
import net.warcar.terrariareference.TerrariaReferenceMod;


@Mod.EventBusSubscriber
public class BossbarOverlay {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int w = event.getWindow().getScaledWidth();
			int h = event.getWindow().getScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			World world = null;
			double x = 0;
			double y = 0;
			double z = 0;
			PlayerEntity entity = Minecraft.getInstance().player;
			if (entity != null) {
				world = entity.world;
				x = entity.getPosX();
				y = entity.getPosY();
				z = entity.getPosZ();
			}
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableAlphaTest();
			if (IfBossProcedure.executeProcedure(
					Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
							.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("terraria_reference:textures/screens/bossbar_base.png"));
				Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -126, 30, 0, 0, 258, 30, 258, 30);
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("terraria_reference:textures/screens/ui_bossbar.png"));
				for (int i = 0; i < (int) ((curHp(x, y, z, world, entity) / maxHp(x, y, z, world, entity)) * 227); i++) {
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -108 + i, 39, 0, 0, 1, 11, 1, 11);
				}
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("terraria_reference:textures/screens/bossbar_cap.png"));
				Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -126, 30, 0, 0, 258, 30, 258, 30);
				
				BossEntity _ent = CurBossProcedure.executeProcedure(world, x, y, z);
				if (_ent != null){
					ResourceLocation _loc = ForgeRegistries.ENTITIES.getKey(_ent.getType());
					String _str = "textures/screens/" + _loc.getPath() + ".png";
					//String _str = "textures/screens/eye_of_cthulhu.png";
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(_loc.getNamespace(), _str));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -125, 38, 0, 0, 16, 16, 16, 16);
				}
				
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						BossbarValueProcedure.executeProcedure(Stream
								.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
								.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)),
						posX + -27, 41, -1);
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	static double curHp(double x, double y, double z, World world, Entity entity) {
		return BossbarLogicProcedure
				.curHp(Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
	}

	static double maxHp(double x, double y, double z, World world, Entity entity) {
		return BossbarLogicProcedure
			.maxHp(Stream.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
	}
}
