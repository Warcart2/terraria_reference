package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.warcar.terrariareference.BossEntity;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

public class ExitProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldUnload(WorldEvent.Unload event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			//executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure Exit!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		{
			double worldSize = 3000000;
			List<BossEntity> _entfound = world.getEntitiesWithinAABB(BossEntity.class, new AxisAlignedBB(-worldSize, 0.0, -worldSize, worldSize, 255.0, worldSize), null).stream().sorted(new Object() {
				Comparator<BossEntity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparing((Function<BossEntity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
				}
			}.compareDistOf(0, 0, 0)).collect(Collectors.toList());
			for (BossEntity entityiterator : _entfound) {
				if (!entityiterator.world.isRemote())
					entityiterator.remove();
			}
		}
	}
}
