package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.block.ShimmerBlock;
import net.warcar.terrariareference.block.AetheriumBlockBlock;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.FlowingFluidBlock;

import java.util.Map;

public class ShimmerNeighbourBlockChangesProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure ShimmerNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure ShimmerNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure ShimmerNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure ShimmerNeighbourBlockChanges!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if ((world.getBlockState(new BlockPos(x + 1, y + 0, z + 0))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x + 1, y + 0, z + 0))).getBlock() == ShimmerBlock.block)
				|| (world.getBlockState(new BlockPos(x - 1, y + 0, z + 0))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x - 1, y + 0, z + 0))).getBlock() == ShimmerBlock.block)
				|| (world.getBlockState(new BlockPos(x + 0, y + 1, z + 0))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x + 0, y + 1, z + 0))).getBlock() == ShimmerBlock.block)
				|| (world.getBlockState(new BlockPos(x + 0, y - 1, z + 0))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x + 0, y - 1, z + 0))).getBlock() == ShimmerBlock.block)
				|| (world.getBlockState(new BlockPos(x + 0, y + 0, z + 1))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x + 0, y + 0, z + 1))).getBlock() == ShimmerBlock.block)
				|| (world.getBlockState(new BlockPos(x + 0, y + 0, z - 1))).getBlock() instanceof FlowingFluidBlock && !((world.getBlockState(new BlockPos(x + 0, y + 0, z - 1))).getBlock() == ShimmerBlock.block)) {
			world.setBlockState(new BlockPos(x, y, z), AetheriumBlockBlock.block.getDefaultState(), 3);
		}
	}
}
