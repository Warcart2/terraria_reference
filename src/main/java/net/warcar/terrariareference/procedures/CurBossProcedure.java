package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.BossEntity;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

public class CurBossProcedure {

	public static BossEntity executeProcedure(IWorld world, double x, double y, double z) {
		return world.getEntitiesWithinAABB(BossEntity.class, new AxisAlignedBB(x - (100d), y - (100d), z - (100d), x + (100d), y + (100d), z + (100d)), null).stream().sorted(new Object() {
			Comparator<BossEntity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparing((Function<BossEntity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
	}
}
