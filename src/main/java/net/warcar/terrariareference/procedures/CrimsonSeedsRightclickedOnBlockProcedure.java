package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class CrimsonSeedsRightclickedOnBlockProcedure {

	public static ActionResultType executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure CrimsonSeedsRightclickedOnBlock!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure CrimsonSeedsRightclickedOnBlock!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure CrimsonSeedsRightclickedOnBlock!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure CrimsonSeedsRightclickedOnBlock!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure CrimsonSeedsRightclickedOnBlock!");
			return ActionResultType.PASS;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		BlockState newBlock = Blocks.AIR.getDefaultState();
		newBlock = (world.getBlockState(new BlockPos(x, y, z)));
		if (newBlock.getMaterial() == net.minecraft.block.material.Material.EARTH || newBlock.getMaterial() == net.minecraft.block.material.Material.ORGANIC) {
			newBlock = CrimsonVersionProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("blockstate", newBlock)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			if (!(newBlock.getBlock() == (world.getBlockState(new BlockPos(x, y, z))).getBlock())) {
				(itemstack).shrink((int) 1);
				world.setBlockState(new BlockPos(x, y, z), newBlock, 3);
				return ActionResultType.CONSUME;
			}
		}
		return ActionResultType.FAIL;
	}
}
