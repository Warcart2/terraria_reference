package net.warcar.terrariareference.procedures;

import org.jline.terminal.Size;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.Collections;

public class OceanFinderProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure OceanFinder!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure OceanFinder!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure OceanFinder!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure OceanFinder!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		boolean found = false;
		double SubX = 0;
		double SubZ = 0;
		double PreDist = 0;
		double PreX = 0;
		double PreZ = 0;
		double SubY = 0;
		double Resolution = 0;
		double Size = 0;
		Size = 1000;
		PreDist = Size * 2;
		Resolution = 16;
		SubX = Size / (-2);
		for (int index0 = 0; index0 < (int) (Size / Resolution); index0++) {
			SubZ = Size / Resolution;
			for (int index1 = 0; index1 < (int) (Size); index1++) {
				if ((world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x + SubX, 60, z + SubZ))) != null
						&& world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x + SubX, 60, z + SubZ))).equals(new ResourceLocation("snowy_beach"))
						|| world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x + SubX, 60, z + SubZ))) != null
								&& world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(world.getBiome(new BlockPos(x + SubX, 60, z + SubZ))).equals(new ResourceLocation("beach")))
						&& Math.sqrt(Math.pow(SubX, 2) + Math.pow(SubZ, 2)) < PreDist) {
					PreX = SubX;
					PreZ = SubZ;
					PreDist = Math.sqrt(Math.pow(SubX, 2) + Math.pow(SubZ, 2));
					found = true;
				}
				SubZ = SubZ + Resolution;
			}
			SubX = SubX + Resolution;
		}
		if (found && PreDist != 0) {
			SubY = 255;
			for (int index2 = 0; index2 < (int) (255); index2++) {
				if (!((world.getBlockState(new BlockPos(x + PreX, SubY, z + PreZ))).getBlock() == Blocks.AIR)) {
					{
						Entity _ent = entity;
						_ent.setPositionAndUpdate((x + PreX), SubY, (z + PreZ));
						if (_ent instanceof ServerPlayerEntity) {
							((ServerPlayerEntity) _ent).connection.setPlayerLocation((x + PreX), SubY, (z + PreZ), _ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
						}
					}
					break;
				}
				SubY = SubY - 1;
			}
		}
	}
}
