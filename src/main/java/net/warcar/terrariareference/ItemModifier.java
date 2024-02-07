package net.warcar.terrariareference;

import java.util.HashMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ItemModifier {
	public static class RegistryEntry implements IForgeRegistryEntry<RegistryEntry> {
		protected ItemModifier modifier;
		protected ResourceLocation name;
		public RegistryEntry(ItemModifier mod, ResourceLocation name) {
			this.modifier = mod;
			this.name = name;
		}
		public ItemModifier getModifier() {
			return this.modifier;
		}
		@Override
		public Class<RegistryEntry> getRegistryType(){
			return null;
		}
		@Override
		public ResourceLocation getRegistryName(){
			return this.name;
		}
		@Override
		public RegistryEntry setRegistryName(ResourceLocation newName){
			this.name = newName;
			return this;
		}
	}
	public static final ItemModifier EMPTY = new ItemModifier(new HashMap<>());
	protected HashMap<Attribute, AttributeModifier> attributes = new HashMap<>();
	protected boolean hidden = false;
	public ItemModifier(HashMap<Attribute, AttributeModifier> attributes, boolean hidden) {
		this.attributes = attributes;
		this.hidden = hidden;
	}
	public ItemModifier(HashMap<Attribute, AttributeModifier> attributes) {
		this(attributes, false);
	}
	public HashMap<Attribute, AttributeModifier> getAttributes() {return this.attributes;}
	public boolean isHidden() {return this.hidden;}
}
