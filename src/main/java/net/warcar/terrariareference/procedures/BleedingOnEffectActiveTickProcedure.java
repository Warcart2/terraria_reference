package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class BleedingOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure BleedingOnEffectActiveTick!");
			return;
		}
		if (dependencies.get("amplifier") == null) {
			if (!dependencies.containsKey("amplifier"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency amplifier for procedure BleedingOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double amplifier = dependencies.get("amplifier") instanceof Integer ? (int) dependencies.get("amplifier") : (double) dependencies.get("amplifier");
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).attackEntityFrom(new DamageSource("bleeding").setDamageBypassesArmor(), (float) (4 * amplifier));
		}
	}
}
