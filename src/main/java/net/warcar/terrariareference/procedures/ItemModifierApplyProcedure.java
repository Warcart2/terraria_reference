package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import net.minecraft.item.ItemStack;
import net.warcar.terrariareference.procedures.AllItemModifiersProcedure;
import net.warcar.terrariareference.ItemModifier;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.nbt.CompoundNBT;
import java.util.Set;
import java.util.List;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.SwordItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ShovelItem;
import net.warcar.terrariareference.procedures.IsToolProcedure;
import net.minecraft.item.ArmorItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

public class ItemModifierApplyProcedure {
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
		ItemAttributeModifierEvent event = (ItemAttributeModifierEvent) dependencies.get("event");
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure ItemModifierApply!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		ItemModifier att = AllItemModifiersProcedure.All.get(itemstack.getOrCreateTag().getString("modifier"));
		if (att == null) {return;}
		HashMap<Attribute, AttributeModifier> attributes = att.getAttributes();
		Object[] arr = attributes.keySet().toArray();
		EquipmentSlotType slot = itemstack.getEquipmentSlot();
		ICuriosHelper helper = CuriosApi.getCuriosHelper();
		Object[] tags = helper.getCurioTags(itemstack.getItem()).toArray();
		if (IsToolProcedure.is(itemstack)){
				slot = EquipmentSlotType.MAINHAND;
		}else if (itemstack.getItem() instanceof ArmorItem){
			slot = ((ArmorItem) itemstack.getItem()).getEquipmentSlot();
		}
		for (Object attribute : arr) {
			if (event.getSlotType() == slot) {
				event.addModifier((Attribute) attribute, attributes.get((Attribute) attribute));
			}
			AttributeModifier mod = attributes.get((Attribute) attribute);
			for (Object tag : tags){
				helper.addModifier(itemstack, (Attribute) attribute, mod.getName(), null, mod.getAmount(), mod.getOperation(), (String) tag);
			}
		}
	}
}
