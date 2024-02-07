package net.warcar.terrariareference.procedures;

import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ShovelItem;

public class IsToolProcedure {

	public static boolean is(ItemStack itemstack) {
		return itemstack.getItem() instanceof SwordItem || itemstack.getItem() instanceof AxeItem || 
			itemstack.getItem() instanceof PickaxeItem || itemstack.getItem() instanceof HoeItem || itemstack.getItem() instanceof ShovelItem;
	}
}
