package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.item.MagmaScullItem;
import net.warcar.terrariareference.item.ObsidianScullItem;
import net.warcar.terrariareference.item.MoltenScullRoseItem;
import net.warcar.terrariareference.item.LavaWandersItem;
import net.warcar.terrariareference.item.TerrasparkBootsItem;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

public class NoHeatedDamageProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingAttackEvent event) {
			if (event != null && event.getEntity() != null) {
				LivingEntity entity = event.getEntityLiving();
				Entity sourceentity = event.getSource().getTrueSource();
				Entity immediatesourceentity = event.getSource().getImmediateSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("immediatesourceentity", immediatesourceentity);
				dependencies.put("event", event);
				dependencies.put("source", event.getSource());
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure NoHeatedDamage!");
			return;
		}
		DamageSource source = (DamageSource)dependencies.get("source");
		LivingEntity entity = (LivingEntity) dependencies.get("entity");
		ICuriosHelper helper = CuriosApi.getCuriosHelper();
		if ((helper.findEquippedCurio(ObsidianScullItem.block, entity).isPresent() 
										|| helper.findEquippedCurio(MagmaScullItem.block, entity).isPresent() 
										|| helper.findEquippedCurio(MoltenScullRoseItem.block, entity).isPresent()
										|| helper.findEquippedCurio(LavaWandersItem.block, entity).isPresent() 
										|| helper.findEquippedCurio(TerrasparkBootsItem.block, entity).isPresent()) && source == DamageSource.HOT_FLOOR) {
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
