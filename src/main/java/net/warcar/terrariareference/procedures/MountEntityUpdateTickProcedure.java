package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.entity.Entity;

import java.util.Map;

public class MountEntityUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure MountEntityUpdateTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getRidingEntity()) == null) {
			if (!entity.world.isRemote())
				entity.remove();
		}
	}
}
