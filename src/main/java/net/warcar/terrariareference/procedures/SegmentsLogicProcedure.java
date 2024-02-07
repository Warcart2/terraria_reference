package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.SegmentedBossEntity;
import net.warcar.terrariareference.BossEntity;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

public class SegmentsLogicProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure SegmentsLogic!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity prev = null;
		Entity next = null;
		double num = 0;
		double pitchSet = 0;
		double xDif = 0;
		double yDif = 0;
		double zDif = 0;
		double yawSet = 0;
		if (!(entity instanceof SegmentedBossEntity)) {
			return;
		}
		prev = ((SegmentedBossEntity) entity).getPrev();
		next = ((SegmentedBossEntity) entity).getNext();
		if (prev == null) {
			prev = entity;
		}
		if (!prev.isAlive()) {
			((SegmentedBossEntity) entity).setPrev(null);
			return;
		}
		if (next == null) {
			next = entity;
		}
		if (!next.isAlive()) {
			((SegmentedBossEntity) entity).setPrev(null);
			return;
		}
		num = Math.sqrt(Math.pow(entity.getWidth(), 2) * 2);
		if (next == entity && !(prev == entity)) {
			xDif = (prev.getPosX() - prev.getLookVec().x * (num / 2)) - entity.getPosX();
			yDif = (prev.getPosY() - prev.getLookVec().y * (num / 2)) - entity.getPosY();
			zDif = (prev.getPosZ() - prev.getLookVec().z * (num / 2)) - entity.getPosZ();
			yawSet = ((-180) / Math.PI) * Math.acos(xDif / Math.sqrt(Math.pow(xDif, 2) + Math.pow(zDif, 2)));
			pitchSet = ((-180) / Math.PI) * Math.acos(Math.sqrt(Math.pow(xDif, 2) + Math.pow(zDif, 2)) / Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2) + Math.pow(zDif, 2)));
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate((prev.getPosX() - prev.getLookVec().x * num), (prev.getPosY() - prev.getLookVec().y * num), (prev.getPosZ() - prev.getLookVec().z * num));
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation((prev.getPosX() - prev.getLookVec().x * num), (prev.getPosY() - prev.getLookVec().y * num), (prev.getPosZ() - prev.getLookVec().z * num), _ent.rotationYaw,
							_ent.rotationPitch, Collections.emptySet());
				}
			}
			((BossEntity) entity).setNoAI(true);
		} else if (!(prev == entity)) {
			xDif = (prev.getPosX() - prev.getLookVec().x * (num / 2)) - (next.getPosX() + next.getLookVec().x * (num / 2));
			yDif = (prev.getPosY() - prev.getLookVec().y * (num / 2)) - (next.getPosY() + next.getLookVec().y * (num / 2));
			zDif = (prev.getPosZ() - prev.getLookVec().z * (num / 2)) - (next.getPosZ() + next.getLookVec().z * (num / 2));
			pitchSet = ((-180) / Math.PI) * Math.acos(Math.sqrt(Math.pow(xDif, 2) + Math.pow(zDif, 2)) / Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2) + Math.pow(zDif, 2)));
			yawSet = ((-180) / Math.PI) * Math.acos(xDif / Math.sqrt(Math.pow(xDif, 2) + Math.pow(zDif, 2)));
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(((prev.getPosX() - prev.getLookVec().x * (num / 2) + next.getPosX() + next.getLookVec().x * (num / 2)) / 2),
						((prev.getPosY() - prev.getLookVec().y * (num / 2) + next.getPosY() + next.getLookVec().y * (num / 2)) / 2), ((prev.getPosZ() - prev.getLookVec().z * (num / 2) + next.getPosZ() + next.getLookVec().z * (num / 2)) / 2));
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(((prev.getPosX() - prev.getLookVec().x * (num / 2) + next.getPosX() + next.getLookVec().x * (num / 2)) / 2),
							((prev.getPosY() - prev.getLookVec().y * (num / 2) + next.getPosY() + next.getLookVec().y * (num / 2)) / 2), ((prev.getPosZ() - prev.getLookVec().z * (num / 2) + next.getPosZ() + next.getLookVec().z * (num / 2)) / 2),
							_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
				}
			}
			entity.rotationYaw = (float) (yawSet);
			entity.setRenderYawOffset(entity.rotationYaw);
			entity.prevRotationYaw = entity.rotationYaw;
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
				((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
				((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
			}
			entity.rotationPitch = (float) (pitchSet);
		}
	}
}
