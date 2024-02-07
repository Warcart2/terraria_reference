package net.warcar.terrariareference.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.warcar.terrariareference.item.TerrasparkBootsItem;
import net.warcar.terrariareference.item.SpectreBootsItem;
import net.warcar.terrariareference.item.SandstormBootsItem;
import net.warcar.terrariareference.item.RocketBootsItem;
import net.warcar.terrariareference.item.LightningBootsItem;
import net.warcar.terrariareference.TerrariaReferenceModVariables;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

public class FlyHighProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure FlyHigh!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).Fly && entity.getPersistentData().getDouble("FlyRemain") > 0
				&& (CuriosApi.getCuriosHelper().findEquippedCurio(RocketBootsItem.block, (LivingEntity) entity).isPresent() || CuriosApi.getCuriosHelper().findEquippedCurio(SpectreBootsItem.block, (LivingEntity) entity).isPresent()
						|| CuriosApi.getCuriosHelper().findEquippedCurio(LightningBootsItem.block, (LivingEntity) entity).isPresent() || CuriosApi.getCuriosHelper().findEquippedCurio(SandstormBootsItem.block, (LivingEntity) entity).isPresent()
						|| CuriosApi.getCuriosHelper().findEquippedCurio(TerrasparkBootsItem.block, (LivingEntity) entity).isPresent())) {
			if ((entity instanceof LivingEntity) ? ((LivingEntity) entity).isElytraFlying() : false) {
				entity.setMotion((entity.getMotion().getX() + entity.getLookVec().x / 10), (entity.getMotion().getY() + entity.getLookVec().y / 10), (entity.getMotion().getZ() + entity.getLookVec().z / 10));
			} else {
				entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY() + 0.1), (entity.getMotion().getZ()));
			}
			entity.getPersistentData().putDouble("FlyRemain", (entity.getPersistentData().getDouble("FlyRemain") - 1));
		}
		if (entity.isOnGround()) {
			entity.getPersistentData().putDouble("FlyRemain", 31);
		}
		entity.getPersistentData().putDouble("Fly", (entity.getPersistentData().getDouble("Fly") + -1));
	}
}
