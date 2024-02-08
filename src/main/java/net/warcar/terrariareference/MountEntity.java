package net.warcar.terrariareference;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.world.server.ServerWorld;
import net.warcar.terrariareference.procedures.MountEntityUpdateTickProcedure;
import net.java.games.input.Rumbler;
import java.util.Map;
import java.util.HashMap;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.MathHelper;

public class MountEntity extends TameableEntity {
	public MountEntity(EntityType<? extends TameableEntity> type, World world) {
		super(type, world);
	}
	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ent){return ent;}
	protected void registerGoals() {}
	@Override
	public void travel(Vector3d dir) {
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
		if (this.isBeingRidden()) {
			this.rotationYaw = entity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
			this.renderYawOffset = entity.rotationYaw;
			this.rotationYawHead = entity.rotationYaw;
			this.stepHeight = 1.0F;
			if (entity instanceof LivingEntity) {
				LivingEntity _ent = ((LivingEntity) entity);
				this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
				float forward = _ent.moveForward;
				float strafe = _ent.moveStrafing;
				float jump = _ent.moveVertical * this.jumpMovementFactor;
				if (jump > 0f) {this.jump();}
				super.travel(new Vector3d(strafe, jump, forward));
			}
			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.getPosX() - this.prevPosX;
			double d0 = this.getPosZ() - this.prevPosZ;
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
			if (f1 > 1.0F)
				f1 = 1.0F;
			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
			return;
		}
		this.stepHeight = 0.5F;
		this.jumpMovementFactor = 0.02F;
		super.travel(dir);
	}
	public void livingTick() {
		super.livingTick();
		Map<String, Object> run = new HashMap<>();
		run.put("entity", this);
		MountEntityUpdateTickProcedure.executeProcedure(run);
	}
}
