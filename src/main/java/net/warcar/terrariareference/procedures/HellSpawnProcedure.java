package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Collections;

public class HellSpawnProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure HellSpawn!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure HellSpawn!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure HellSpawn!");
			return false;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure HellSpawn!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double subY = 0;
		double subX = 0;
		subX = 0;
		for (int index0 = 0; index0 < (int) (4000); index0++) {
			subY = 0;
			for (int index1 = 0; index1 < (int) (128); index1++) {
				if (((world.getBlockState(new BlockPos(subX + x, subY - 1, z))).getBlock() == Blocks.NETHERRACK || (world.getBlockState(new BlockPos(subX + x, subY - 1, z))).getBlock() == Blocks.SOUL_SAND
						|| (world.getBlockState(new BlockPos(subX + x, subY - 1, z))).getBlock() == Blocks.GRAVEL || (world.getBlockState(new BlockPos(subX + x, subY - 1, z))).getBlock() == Blocks.NETHER_BRICKS)
						&& (world.getBlockState(new BlockPos(subX + x, subY + 0, z))).getMaterial() == net.minecraft.block.material.Material.AIR
						&& (world.getBlockState(new BlockPos(subX + x, subY + 1, z))).getMaterial() == net.minecraft.block.material.Material.AIR) {
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate((subX + x), subY, z);
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation((subX + x), subY, z, _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
						}
					}
					return true;
				}
				subY = 1 + subY;
			}
			subX = 1 + subX;
		}
		return false;
	}
}
