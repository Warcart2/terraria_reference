package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.SegmentedBossEntity;
import net.warcar.terrariareference.BossEntity;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

public class BossbarLogicProcedure {
	public static double curHp(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure BossbarLogic!");
			return 0;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		BossEntity boss = null;
		boss = world.getEntitiesWithinAABB(BossEntity.class, new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null).stream().sorted(new Object() {
			Comparator<BossEntity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparing((Function<BossEntity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if (boss == null)
			return -1;
		if (boss instanceof SegmentedBossEntity)
			return ((SegmentedBossEntity) boss).getHpPoolAll();
		return boss.getFullHP();
	}

	public static double maxHp(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure BossbarLogic!");
			return 0;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure BossbarLogic!");
			return 0;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		BossEntity boss = null;
		boss = world.getEntitiesWithinAABB(BossEntity.class, new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null).stream().sorted(new Object() {
			Comparator<BossEntity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparing((Function<BossEntity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if (boss == null)
			return -1;
		if (boss instanceof SegmentedBossEntity)
			return ((SegmentedBossEntity) boss).getHpPoolMaxAll();
		return boss.getFullMaxHP();
	}
}
