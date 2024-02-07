package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.warcar.terrariareference.item.ObsidianRoseItem;
import net.warcar.terrariareference.item.MoltenScullRoseItem;
import net.warcar.terrariareference.item.LavaWandersItem;
import net.warcar.terrariareference.item.TerrasparkBootsItem;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.util.DamageSource;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.api.CuriosApi;
import net.minecraft.entity.LivingEntity;

public class NoLavaDamageProcedure {
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
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure NoLavaDamage!");
			return;
		}
		LivingEntity entity = (LivingEntity) dependencies.get("entity");
		double amount = (double) dependencies.get("amount");
		DamageSource source = (DamageSource) dependencies.get("source");
		double dmg = 0;
		ICuriosHelper helper = CuriosApi.getCuriosHelper();
		if (entity.getPersistentData().getBoolean("lava_charm") && source == DamageSource.LAVA) {
			if (dependencies.get("event") != null) {
				Object _obj = dependencies.get("event");
				if (_obj instanceof Event) {
					Event _evt = (Event) _obj;
					if (_evt.isCancelable())
						_evt.setCanceled(true);
						entity.extinguish();
				}
			}
		}
		else if ((helper.findEquippedCurio(ObsidianRoseItem.block, entity).isPresent()
							|| helper.findEquippedCurio(MoltenScullRoseItem.block, entity).isPresent()
							|| helper.findEquippedCurio(LavaWandersItem.block, entity).isPresent()
							|| helper.findEquippedCurio(TerrasparkBootsItem.block, entity).isPresent()) && source == DamageSource.LAVA) {
			dmg = amount / 3;
			if (dependencies.get("event") != null) {
				Object _obj = dependencies.get("event");
				if (_obj instanceof Event) {
					Event _evt = (Event) _obj;
					if (_evt.isCancelable())
						_evt.setCanceled(true);
				}
			}
			entity.attackEntityFrom(new DamageSource("lava_lowered").setDamageBypassesArmor(), (float) dmg);
		}
	}
}
