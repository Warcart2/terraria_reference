package net.warcar.terrariareference.procedures;

import net.minecraft.world.IWorld;

import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.Map;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.ISeedReader;

public class IsCrimsonProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure IsCrimson!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ISeedReader) {
			return(Math.abs(((ISeedReader) world).getSeed() % 2) == 1);
		}
		return false;
	}
}
