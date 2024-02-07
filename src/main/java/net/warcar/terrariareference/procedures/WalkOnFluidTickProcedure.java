package net.warcar.terrariareference.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;
import java.util.Collections;
import net.minecraft.world.World;
import java.util.Optional;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;

public class WalkOnFluidTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure WalkOnFluidTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure WalkOnFluidTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure WalkOnFluidTick!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure WalkOnFluidTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = entity.world;
		if (world.isRemote()){
			return;
		}
		if (entity.getPosY() != Math.floor(entity.getPosY()) + 0.9) {
			{
				if (entity instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) entity).setPositionAndUpdate(entity.getPosX(), (Math.floor(entity.getPosY()) + 0.9), entity.getPosZ());
					entity.setMotion((entity.getMotion().getX()), 0, (entity.getMotion().getZ()));
				}
			}
		}
		entity.setMotion((entity.getMotion().getX()), Math.max(entity.getMotion().getY(), 0), (entity.getMotion().getZ()));
		entity.getPersistentData().putDouble("FlyRemain", 21);
		entity.setOnGround(true);
	}
}
