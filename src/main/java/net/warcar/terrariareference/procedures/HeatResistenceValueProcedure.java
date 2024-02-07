package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceModVariables;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.entity.Entity;

import java.util.Map;

public class HeatResistenceValueProcedure {

	public static String executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure HeatResistenceValue!");
			return "";
		}
		Entity entity = (Entity) dependencies.get("entity");
		return "" + Math.round((entity.getCapability(TerrariaReferenceModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new TerrariaReferenceModVariables.PlayerVariables())).lavaResist / 20);
	}
}
