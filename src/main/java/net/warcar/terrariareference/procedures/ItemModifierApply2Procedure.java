package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.HashMap;

public class ItemModifierApply2Procedure {
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
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure ItemModifierApply2!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		String modifierName = "";
		if (!(itemstack).isStackable()) {
			if (!((itemstack.getOrCreateTag().getString("modifier")).equals("") || (itemstack.getOrCreateTag().getString("modifier")).equals("empty"))) {
				modifierName = "modifier." + itemstack.getOrCreateTag().getString("mod_id") + "." + itemstack.getOrCreateTag().getString("modifier");
				if ((new TranslationTextComponent(modifierName).getString()).equals(modifierName)) {
					(itemstack).setDisplayName(new StringTextComponent((itemstack.getOrCreateTag().getString("modifier") + " " + new TranslationTextComponent(itemstack.getTranslationKey()).getString())));
				} else {
					(itemstack).setDisplayName(new StringTextComponent((new TranslationTextComponent(modifierName).getString() + " " + new TranslationTextComponent(itemstack.getTranslationKey()).getString())));
				}
			} else {
				(itemstack).setDisplayName(new StringTextComponent((new TranslationTextComponent(itemstack.getTranslationKey()).getString())));
			}
		}
	}
}
