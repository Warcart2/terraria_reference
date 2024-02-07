/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.warcar.terrariareference.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.EntityType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerrariaReferenceModAttributes {
	public static final Attribute CRITICAL_STRIKE_CHANCE = new RangedAttribute("attribute.terraria_reference.critical_strike_chance", 4, 0, 100).setShouldWatch(true);
	public static final Attribute MAX_MANA = new RangedAttribute("attribute.terraria_reference.max_mana", 20, 1, 512).setShouldWatch(true);
	public static final Attribute MANA_REGENERATION = new RangedAttribute("attribute.terraria_reference.mana_regeneration", 1, 1, 10).setShouldWatch(true);
	public static final Attribute MAGIC_DAMAGE = new RangedAttribute("attribute.terraria_reference.magic_damage", 0, 0, 2048).setShouldWatch(true);
	public static final Attribute HP_REGEN = new RangedAttribute("attribute.terraria_reference.hp_regen", 1, 0, 32).setShouldWatch(true);

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Attribute> event) {
		CRITICAL_STRIKE_CHANCE.setRegistryName("terraria_reference", "critical_strike_chance");
		event.getRegistry().register(CRITICAL_STRIKE_CHANCE);
		MAX_MANA.setRegistryName("terraria_reference", "max_mana");
		event.getRegistry().register(MAX_MANA);
		MANA_REGENERATION.setRegistryName("terraria_reference", "mana_regeneration");
		event.getRegistry().register(MANA_REGENERATION);
		MAGIC_DAMAGE.setRegistryName("terraria_reference", "magic_damage");
		event.getRegistry().register(MAGIC_DAMAGE);
		HP_REGEN.setRegistryName("terraria_reference", "hp_regen");
		event.getRegistry().register(HP_REGEN);
	}

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, CRITICAL_STRIKE_CHANCE);
		event.add(EntityType.PLAYER, CRITICAL_STRIKE_CHANCE);
		event.add(EntityType.PLAYER, MAX_MANA);
		event.add(EntityType.PLAYER, MANA_REGENERATION);
		event.add(EntityType.PLAYER, MAGIC_DAMAGE);
		event.add(EntityType.PLAYER, HP_REGEN);
	}

	@Mod.EventBusSubscriber
	private static class Utils {
		@SubscribeEvent
		public static void persistAttributes(PlayerEvent.Clone event) {
			PlayerEntity oldP = event.getOriginal();
			PlayerEntity newP = (PlayerEntity) event.getEntity();
			newP.getAttribute(CRITICAL_STRIKE_CHANCE).setBaseValue(oldP.getAttribute(CRITICAL_STRIKE_CHANCE).getBaseValue());
			newP.getAttribute(MANA_REGENERATION).setBaseValue(oldP.getAttribute(MANA_REGENERATION).getBaseValue());
		}
	}
}
