package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class IsInHotbarProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure IsInHotbar!");
			return false;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure IsInHotbar!");
			return false;
		}
		if (dependencies.get("slot") == null) {
			if (!dependencies.containsKey("slot"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency slot for procedure IsInHotbar!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double slot = dependencies.get("slot") instanceof Integer ? (int) dependencies.get("slot") : (double) dependencies.get("slot");
		return slot >= 0 && slot < 9 || ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == itemstack.getItem();
	}
}
