package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.item.TerrasparkBootsItem;
import net.warcar.terrariareference.item.SpectreBootsItem;
import net.warcar.terrariareference.item.SandstormBootsItem;
import net.warcar.terrariareference.item.LightningBootsItem;
import net.warcar.terrariareference.item.HermesBootsItem;
import net.warcar.terrariareference.item.DuneriderBootsItem;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.api.CuriosApi;
import net.minecraft.entity.ai.attributes.Attributes;

public class HermesBootsTickEventProcedure {
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
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure HermesBootsTickEvent!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		ICuriosHelper helper = CuriosApi.getCuriosHelper();
		if (itemstack.getItem() == SpectreBootsItem.block || itemstack.getItem() == DuneriderBootsItem.block || itemstack.getItem() == HermesBootsItem.block) {
			helper.addModifier(itemstack, Attributes.MOVEMENT_SPEED, "terraria_reference." + "hermes_boots", UUID.fromString("7c3d51c9-56c3-4aa7-81cb-9feeebb30c9b"), 0.4, AttributeModifier.Operation.MULTIPLY_TOTAL, "accesory");
		}
		if (itemstack.getItem() == TerrasparkBootsItem.block || itemstack.getItem() == SandstormBootsItem.block || itemstack.getItem() == LightningBootsItem.block) {
			helper.addModifier(itemstack, Attributes.MOVEMENT_SPEED, "terraria_reference." + "hermes_boots", UUID.fromString("7c3d51c9-56c3-4aa7-81cb-9feeebb30c9b"), 0.48, AttributeModifier.Operation.MULTIPLY_TOTAL, "accesory");
		}
		if ((itemstack.getItem() == TerrasparkBootsItem.block || itemstack.getItem() == SandstormBootsItem.block || itemstack.getItem() == DuneriderBootsItem.block) && itemstack.getOrCreateTag().getBoolean("sanded")) {
			helper.addModifier(itemstack, Attributes.MOVEMENT_SPEED, "terraria_reference." + "sandspeed", UUID.fromString("500f7119-f159-48ef-b419-194584673e56"), 0.5, AttributeModifier.Operation.MULTIPLY_TOTAL, "accesory");
		}
	}
}
