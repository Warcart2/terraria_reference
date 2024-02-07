package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;

public class BleedingActiveTickConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("duration") == null) {
			if (!dependencies.containsKey("duration"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency duration for procedure BleedingActiveTickCondition!");
			return false;
		}
		double duration = dependencies.get("duration") instanceof Integer ? (int) dependencies.get("duration") : (double) dependencies.get("duration");
		return duration % 20 == 0;
	}
}
