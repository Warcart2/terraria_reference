package net.warcar.terrariareference;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.warcar.terrariareference.BossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import java.util.HashMap;
import net.warcar.terrariareference.procedures.SegmentsLogicProcedure;
import java.util.List;
import java.util.ArrayList;

public class SegmentedBossEntity extends BossEntity {
	protected SegmentedBossEntity prev;
	protected SegmentedBossEntity next;
	protected boolean fullyConected;
	protected SegmentedBossEntity[] all;
	protected double hpPoolAll;
	protected double hpPoolMaxAll;
	public SegmentedBossEntity(EntityType<BossEntity> type, World world, boolean fullyConected) {
		super(type, world, 1);
		this.fullyConected = fullyConected;
	}
	public void setPrev(SegmentedBossEntity newPrev) {this.prev = newPrev;}
	public void setNext(SegmentedBossEntity newNext) {this.next = newNext;}
	public void setAll(SegmentedBossEntity[] newAll) {
		this.all = newAll;
		this.hpPoolMaxAll = this.hpPoolMax();
	}
	public SegmentedBossEntity getPrev() {return this.prev;}
	public SegmentedBossEntity getNext() {return this.next;}
	public SegmentedBossEntity[] getAll() {return this.all;}
	public double getHpPoolAll() {return this.hpPoolAll;}
	public double getHpPoolMaxAll() {return this.hpPoolMaxAll;}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.fullyConected) {
			//this.controller.attackFrom(source, amount);
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}
	public double hpPool(){
		double out = 0;
		if (this.all == null)
			return -1;
		for (SegmentedBossEntity segment : this.all){
			if (segment == null)
				continue;
			out += segment.getFullHP();
		}
		return out;
	}
	public double hpPoolMax(){
		double out = 0;
		for (SegmentedBossEntity segment : this.all){
			out += segment.getFullMaxHP();
		}
		return out;
	}
	public void livingTick() {
		super.livingTick();
		if (this.world.isRemote())
			return;
		this.hpPoolAll = this.hpPool();
		HashMap<String, Object> proc = new HashMap<>();
		proc.put("entity", this);
		proc.put("world", this.world);
		SegmentsLogicProcedure.executeProcedure(proc);
	}
	public void kill(){
		this.setDead();
	}
}
