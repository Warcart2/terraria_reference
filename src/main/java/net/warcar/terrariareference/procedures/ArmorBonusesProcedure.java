package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.item.ShadowArmorItem;
import net.warcar.terrariareference.item.MoltenArmorItem;
import net.warcar.terrariareference.item.CrimsonArmorItem;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

public class ArmorBonusesProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void addAttributeModifier(ItemAttributeModifierEvent event) {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("itemstack", event.getItemStack());
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure ArmorBonuses!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (dependencies.get("event") instanceof ItemAttributeModifierEvent && ((ItemAttributeModifierEvent) dependencies.get("event")).getSlotType() == EquipmentSlotType.HEAD) {
			ItemAttributeModifierEvent _event = (ItemAttributeModifierEvent) dependencies.get("event");
			if (itemstack.getItem() == ShadowArmorItem.helmet) {
				_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("terraria_reference:critical_strike_chance")),
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "helmet", 5, AttributeModifier.Operation.ADDITION)));
				if (itemstack.getOrCreateTag().getBoolean("set_bonus")) {
					_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED,
							(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "set_bonus", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)));
				}
			}
			if (itemstack.getItem() == CrimsonArmorItem.helmet) {
				_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "helmet", 0.03, AttributeModifier.Operation.MULTIPLY_TOTAL)));
			}
			if (itemstack.getItem() == MoltenArmorItem.helmet) {
				_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("terraria_reference:critical_strike_chance")),
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "helmet", 7, AttributeModifier.Operation.ADDITION)));
				if (itemstack.getOrCreateTag().getBoolean("set_bonus")) {
					_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
							(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "set_bonus", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL)));
				}
			}
		}
		if (dependencies.get("event") instanceof ItemAttributeModifierEvent && ((ItemAttributeModifierEvent) dependencies.get("event")).getSlotType() == EquipmentSlotType.CHEST) {
			ItemAttributeModifierEvent _event = (ItemAttributeModifierEvent) dependencies.get("event");
			if (itemstack.getItem() == ShadowArmorItem.body) {
				_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("terraria_reference:critical_strike_chance")),
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "chestplate", 5, AttributeModifier.Operation.ADDITION)));
			}
			if (itemstack.getItem() == CrimsonArmorItem.body) {
				_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "chestplate", 0.03, AttributeModifier.Operation.MULTIPLY_TOTAL)));
			}
			if (itemstack.getItem() == MoltenArmorItem.body) {
				_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "chestplate", 0.07, AttributeModifier.Operation.MULTIPLY_TOTAL)));
			}
		}
		if (dependencies.get("event") instanceof ItemAttributeModifierEvent && ((ItemAttributeModifierEvent) dependencies.get("event")).getSlotType() == EquipmentSlotType.LEGS) {
			ItemAttributeModifierEvent _event = (ItemAttributeModifierEvent) dependencies.get("event");
			if (itemstack.getItem() == ShadowArmorItem.legs) {
				_event.addModifier(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("terraria_reference:critical_strike_chance")),
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "greaves", 5, AttributeModifier.Operation.ADDITION)));
			}
			if (itemstack.getItem() == CrimsonArmorItem.legs) {
				_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE,
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "greaves", 0.03, AttributeModifier.Operation.MULTIPLY_TOTAL)));
			}
			if (itemstack.getItem() == MoltenArmorItem.legs) {
				_event.addModifier(net.minecraft.entity.ai.attributes.Attributes.ATTACK_SPEED,
						(new AttributeModifier(UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), "terraria_reference." + "greaves", 0.07, AttributeModifier.Operation.MULTIPLY_TOTAL)));
			}
		}
	}
}
