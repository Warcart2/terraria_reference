package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceModVariables;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.warcar.terrariareference.item.MagmaScullItem;
import net.warcar.terrariareference.item.LavaCharmItem;
import net.warcar.terrariareference.item.MoltenScullRoseItem;
import net.warcar.terrariareference.item.LavaWandersItem;
import net.warcar.terrariareference.item.TerrasparkBootsItem;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.api.CuriosApi;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class LavaCharmItemInInventoryTickProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure LavaCharmItemInInventoryTick!");
			return;
		}
		LivingEntity entity = (LivingEntity) dependencies.get("entity");
		int all = CuriosApi.getCuriosHelper().findCurios(entity, LavaCharmItem.block).size() + CuriosApi.getCuriosHelper().findCurios(entity, MagmaScullItem.block).size() + 
							CuriosApi.getCuriosHelper().findCurios(entity, MoltenScullRoseItem.block).size() + CuriosApi.getCuriosHelper().findCurios(entity, LavaWandersItem.block).size() + 
							CuriosApi.getCuriosHelper().findCurios(entity, TerrasparkBootsItem.block).size();
		if (!entity.isInLava()) {
			{
				double _setval = Math.min((140 * all) + 1, (entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).lavaResist + 4);
				entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.lavaResist = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (140 * all) + 1;
				entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.lavaResistMax = _setval;
					capability.syncPlayerVariables(entity);
				});
			}

		}
	}
}
