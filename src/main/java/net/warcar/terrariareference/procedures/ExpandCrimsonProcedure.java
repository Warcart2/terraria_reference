package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceModVariables;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.stream.Stream;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class ExpandCrimsonProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure ExpandCrimson!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure ExpandCrimson!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure ExpandCrimson!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure ExpandCrimson!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		BlockState newBlock = Blocks.AIR.getDefaultState();
		double randY = 0;
		double randZ = 0;
		double randX = 0;
		if (MathHelper.nextInt(new Random(), 0, 3) == 0 || TerrariaReferenceModVariables.MapVariables.get(world).Hardmode) {
			randX = x + MathHelper.nextInt(new Random(), -3, 3);
			randY = y + MathHelper.nextInt(new Random(), -3, 3);
			randZ = z + MathHelper.nextInt(new Random(), -3, 3);
			newBlock = (world.getBlockState(new BlockPos(randX, randY, randZ)));
			newBlock = CrimsonVersionProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("blockstate", newBlock)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			if (!(newBlock.getBlock() == (world.getBlockState(new BlockPos(randX, randY, randZ))).getBlock())) {
				world.setBlockState(new BlockPos(randX, randY, randZ), newBlock, 3);
			}
		}
	}
}
