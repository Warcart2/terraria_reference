package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.block.ShimmerBlock;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;
import java.util.HashMap;

public class ShimmerFillingFailProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onBucketFill(FillBucketEvent event) {
			PlayerEntity entity = event.getPlayer();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = event.getWorld();
			ItemStack itemstack = event.getFilledBucket();
			ItemStack originalitemstack = event.getEmptyBucket();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("itemstack", itemstack);
			dependencies.put("originalitemstack", originalitemstack);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency itemstack for procedure ShimmerFillingFail!");
			return;
		}
		if (dependencies.get("originalitemstack") == null) {
			if (!dependencies.containsKey("originalitemstack"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency originalitemstack for procedure ShimmerFillingFail!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		ItemStack originalitemstack = (ItemStack) dependencies.get("originalitemstack");
		if (itemstack.getItem() == ShimmerBlock.bucket || (originalitemstack).getItem() == ShimmerBlock.bucket) {
			if (dependencies.get("event") != null) {
				Object _obj = dependencies.get("event");
				if (_obj instanceof Event) {
					Event _evt = (Event) _obj;
					if (_evt.isCancelable())
						_evt.setCanceled(true);
				}
			}
		}
	}
}
