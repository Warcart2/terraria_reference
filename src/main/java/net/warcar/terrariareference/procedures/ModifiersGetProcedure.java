package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.ItemModifier;

import java.util.Random;
import java.util.Map;
import net.warcar.terrariareference.procedures.IsToolProcedure;
import java.util.HashMap;
import net.minecraft.item.SwordItem;
import net.minecraft.item.BowItem;

public class ModifiersGetProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
			Entity entity = event.getPlayer();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			ItemStack itemStack = event.getCrafting();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("itemstack", itemStack);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure ModifiersGet!");
			return;
		}
		if (((World)dependencies.get("world")).isRemote()) {return;}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		int rand;
		HashMap<String, ItemModifier> map = new HashMap<>();
		if(itemstack.getItem() instanceof SwordItem) {
			map = AllItemModifiersProcedure.Melee;
		}else if (itemstack.getItem() instanceof BowItem){
			map = AllItemModifiersProcedure.Common;
		}else if (IsToolProcedure.is(itemstack)){
			map = AllItemModifiersProcedure.Tool;
		}
		rand = MathHelper.nextInt(new Random(), 0, (int) map.size());
		if (rand == (int) map.size()){
			itemstack.getOrCreateTag().putString("modifier", "");
		}else {
			itemstack.getOrCreateTag().putString("modifier", (String) map.keySet().toArray()[rand]);
		}
	}
}
