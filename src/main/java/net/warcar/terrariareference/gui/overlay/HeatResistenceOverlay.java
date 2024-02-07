
package net.warcar.terrariareference.gui.overlay;

import net.warcar.terrariareference.procedures.HeatResistenceValueProcedure;
import net.warcar.terrariareference.procedures.ConditionsHeatResistenceProcedure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

@Mod.EventBusSubscriber
public class HeatResistenceOverlay {
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
			if (true) {
				if (ConditionsHeatResistenceProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)))
					Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),

							HeatResistenceValueProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)), posX + -4, posY + -20,
							-4367360);
			}
		}
	}
}
