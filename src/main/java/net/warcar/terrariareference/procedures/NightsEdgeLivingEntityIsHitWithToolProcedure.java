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

public class NightsEdgeLivingEntityIsHitWithToolProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure NightsEdgeLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure NightsEdgeLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency sourceentity for procedure NightsEdgeLivingEntityIsHitWithTool!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		{
			List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB((sourceentity.getPosX()) - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d),
					(sourceentity.getPosY()) - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d), (sourceentity.getPosZ()) - (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d),
					(sourceentity.getPosX()) + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d), (sourceentity.getPosY()) + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d),
					(sourceentity.getPosZ()) + (((LivingEntity) entity).getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() / 2d)), null).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((sourceentity.getPosX()), (sourceentity.getPosY()), (sourceentity.getPosZ()))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator == entity || entityiterator == sourceentity)
						&& (entityiterator instanceof ItemEntity || entityiterator instanceof ItemFrameEntity || entityiterator instanceof ThrowableEntity || entityiterator instanceof ExperienceOrbEntity || entityiterator instanceof EyeOfEnderEntity
								|| entityiterator instanceof FishingBobberEntity || entityiterator instanceof MinecartEntity || entityiterator instanceof BoatEntity)) {
					entityiterator.attackEntityFrom(DamageSource.GENERIC,
							(float) AttackDamageProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", sourceentity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)));
				}
			}
		}
	}
}
