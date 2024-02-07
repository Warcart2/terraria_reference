package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Collections;

public class EndSpawnProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure EndSpawn!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure EndSpawn!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		double sy = 0;
		sy = 255;
		for (int index0 = 0; index0 < (int) (255); index0++) {
			if (!((world.getBlockState(new BlockPos(0, sy, 0))).getMaterial() == net.minecraft.block.material.Material.AIR)) {
				break;
			}
			sy = sy - 1;
		}
		if (sy == 0) {
			world.setBlockState(new BlockPos(0, 0, 0), Blocks.OBSIDIAN.getDefaultState(), 3);
		}
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(0, (sy + 2), 0);
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(0, (sy + 2), 0, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
		}
	}
}
