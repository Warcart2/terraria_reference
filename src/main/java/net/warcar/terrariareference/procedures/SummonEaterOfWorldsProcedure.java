package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.entity.EaterOfWorldsEntity;
import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.SegmentedBossEntity;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import java.util.Map;

public class SummonEaterOfWorldsProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure SummonEaterOfWorlds!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure SummonEaterOfWorlds!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure SummonEaterOfWorlds!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure SummonEaterOfWorlds!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity curent = null;
		Entity prev = null;
		if ((world instanceof World ? (((World) world).getDimensionKey()) : World.OVERWORLD) == (World.OVERWORLD)) {
			SegmentedBossEntity[] all = new SegmentedBossEntity[20];
			for (int index0 = 0; index0 < (int) (20 + 0 * (x + y + z)); index0++) {
				if (!(prev == null)) {
					((SegmentedBossEntity) prev).setNext((SegmentedBossEntity) curent);
				}
				curent = world instanceof World ? new EaterOfWorldsEntity.CustomEntity(EaterOfWorldsEntity.entity, (World) world) : null;
				all[index0] = (SegmentedBossEntity) curent;
				((SegmentedBossEntity) curent).setPrev((SegmentedBossEntity) prev);
				if (world instanceof ServerWorld) {
					curent.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					((MobEntity) curent).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(curent.getPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(curent);
				}
				prev = curent;
			}
			for (SegmentedBossEntity segment : all) {
				segment.setAll(all);
			}
		}
	}
}
