package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.warcar.terrariareference.SegmentedBossEntity;

import net.minecraft.entity.Entity;

import java.util.Map;

public class IfPrevProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure IfPrev!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity a = null;
		a = entity;
		if (!(entity instanceof SegmentedBossEntity)) {
			return false;
		}
		return !(((SegmentedBossEntity) entity).getPrev() == null);
	}
}
