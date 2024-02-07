package net.warcar.terrariareference;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.util.DamageSource;

public class BossEntity extends MonsterEntity {
	protected double hpLayer = 0;
	protected double hpLayerMax = 0;
	public BossEntity(EntityType<BossEntity> type, World world, double hpMultiplier) {
		super(type, world);
		this.experienceValue = 0;
		this.setNoAI(false);
		this.hpLayer = hpMultiplier;
		this.hpLayerMax = hpMultiplier;
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.IN_WALL || source == DamageSource.FALL) {
			return false;
		}
		this.hpLayer -= amount;
		if (-this.hpLayer / this.hpLayerMax >= this.getHealth()){
			return super.attackEntityFrom(source, amount);
		}
		while (this.hpLayer <= 0) {
			this.hpLayer += this.hpLayerMax;
			if (this.getHealth() <= 1) {
				return super.attackEntityFrom(source, amount);
			}
			this.setHealth(this.getHealth() - 1);
			
		}
		return false;
	}

	public double getFullHP() {
		return (this.getHealth() - 1) * this.hpLayerMax + this.hpLayer;
	}

	public double getFullMaxHP() {
		return this.getMaxHealth() * this.hpLayerMax;
	}
	public void livingTick() {
		super.livingTick();
	}

}
