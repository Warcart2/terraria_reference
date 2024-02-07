package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.Map;
import java.util.HashMap;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.ai.attributes.Attributes;
import net.warcar.terrariareference.item.AnkletOfTheWindItem;
import net.warcar.terrariareference.item.AgletItem;

public class AnkletOfTheWindOrAgletItemInInventoryTickProcedure {
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
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		ICuriosHelper helper = CuriosApi.getCuriosHelper();
		UUID uuid = UUID.fromString("7c3d51c9-56c3-4aa7-81cb-9feeebb30c9b");
		if (itemstack.getItem() == AnkletOfTheWindItem.block) {
			helper.addModifier(itemstack, Attributes.MOVEMENT_SPEED, "terraria_reference." + "anklet_of_the_wind", uuid, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL, "accesory");
		}
		else if (itemstack.getItem() == AgletItem.block) {
			helper.addModifier(itemstack, Attributes.MOVEMENT_SPEED, "terraria_reference." + "aglet", uuid, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL, "accesory");
		}
		else if (itemstack.getItem() == AnkletOfTheWindItem.block) {
			helper.addModifier(itemstack, Attributes.ARMOR, "terraria_reference." + "obsidian_scull", uuid, 1, AttributeModifier.Operation.ADDITION, "accesory");
		}
	}
}
