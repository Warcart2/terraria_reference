package net.warcar.terrariareference.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;

public class AttackDamageProcedure {

	public static double executeProcedure(Map<String, Object> dependencies){
			if(dependencies.get("entity") == null) {
				if(!dependencies.containsKey("entity"))
					TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure AttackDamage!");
				return 0;
			}
				Entity entity = (Entity) dependencies.get("entity");
		return ((LivingEntity) entity).getAttribute(net.minecraft.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue();
	}
}
