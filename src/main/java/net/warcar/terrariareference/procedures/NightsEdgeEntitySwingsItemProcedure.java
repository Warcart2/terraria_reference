package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.EyeOfEnderEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;
import java.util.AbstractMap;

public class NightsEdgeEntitySwingsItemProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure NightsEdgeEntitySwingsItem!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure NightsEdgeEntitySwingsItem!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure NightsEdgeEntitySwingsItem!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure NightsEdgeEntitySwingsItem!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure NightsEdgeEntitySwingsItem!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		{
			List<Entity> _entfound = world
					.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d), y - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d),
									z - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d), x + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d),
									y + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d), z + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d)),
							null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator == entity) && (entityiterator instanceof ItemEntity || entityiterator instanceof ItemFrameEntity || entityiterator instanceof ThrowableEntity || entityiterator instanceof ExperienceOrbEntity
						|| entityiterator instanceof EyeOfEnderEntity || entityiterator instanceof FishingBobberEntity || entityiterator instanceof MinecartEntity || entityiterator instanceof BoatEntity)) {
					entityiterator.attackEntityFrom(DamageSource.GENERIC,
							(float) AttackDamageProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)));
				}
			}
		}
	}
}
