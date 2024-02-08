package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.entity.EyeOfChtulhuEntity;
import net.warcar.terrariareference.TerrariaReferenceModVariables;
import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.MountItem;
import net.warcar.terrariareference.MountEntity;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import java.util.Map;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ISlotHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import top.theillusivec4.curios.api.SlotResult;
import net.minecraft.item.Items;
import java.util.Optional;

public class UseMountOnKeyPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure UseMountOnKeyPressed!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure UseMountOnKeyPressed!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure UseMountOnKeyPressed!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure UseMountOnKeyPressed!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure UseMountOnKeyPressed!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		TerrariaReferenceMod.LOGGER.warn("here 0");
		if (/*(entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).mount/*/true) {
			if (world instanceof ServerWorld) {
				Item mount = Items.AIR;
				TerrariaReferenceMod.LOGGER.warn("here 1");
				Optional<SlotResult> _sl = CuriosApi.getCuriosHelper().findCurio((LivingEntity) entity, "mount", 0);
				if (_sl.isPresent()){
					mount = _sl.get().getStack().getItem();
				}
				else {
					return;
				}
				TerrariaReferenceMod.LOGGER.warn("here 2");
				Entity entityToSpawn;
				if (mount instanceof MountItem) {
					entityToSpawn = new MountEntity(((MountItem) mount).getEntity(), (World) world);
				}
				else{
					return;
				}
				TerrariaReferenceMod.LOGGER.warn("here 3");
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
				entityToSpawn.setMotion(entity.getMotion());
				((TameableEntity) entityToSpawn).setTamed(true);
				((TameableEntity) entityToSpawn).setTamedBy((PlayerEntity) entity);
				entity.startRiding(entityToSpawn);
			}
		} else {
			if (!(entity.getRidingEntity()).world.isRemote())
				(entity.getRidingEntity()).remove();
		}
		{
			boolean _setval = !(entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).mount;
			entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.mount = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
