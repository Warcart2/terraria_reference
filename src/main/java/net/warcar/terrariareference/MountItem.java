package net.warcar.terrariareference;

import net.minecraft.item.Item;
import net.minecraft.entity.EntityType;
import net.warcar.terrariareference.MountEntity;

public class MountItem extends Item {
	protected EntityType<? extends MountEntity> entity;
	public MountItem(Item.Properties prop, EntityType mountEntity) {
		super(prop);
		this.entity = mountEntity;
		}
	public EntityType getEntity(){
		return this.entity;
	}
}
