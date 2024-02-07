
package net.warcar.terrariareference.gui.overlay;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.warcar.terrariareference.TerrariaReferenceModVariables;


import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.world.GameType;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;

@Mod.EventBusSubscriber
public class ManaDisplayOverlay {
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
			NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection().getPlayerInfo(((AbstractClientPlayerEntity) entity).getGameProfile().getId());
			if (_npi != null && _npi.getGameType() == GameType.SURVIVAL || _npi.getGameType() == GameType.ADVENTURE){
				for (int i = 0; i < (int) ((entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).Mana / 10); i++) {
					if ((i % 2) == 0){
						Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("terraria_reference:textures/screens/mana_icon_2.png"));
						Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), w - 16, (int) (16 * (int) (i / 2)), 0, 0, 16, 16, 16, 16);
					}else {
						Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("terraria_reference:textures/screens/mana_icon_1.png"));
						Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), w - 16, (int) (16 * (int) (i / 2)), 0, 0, 16, 16, 16, 16);
					}

				}
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
