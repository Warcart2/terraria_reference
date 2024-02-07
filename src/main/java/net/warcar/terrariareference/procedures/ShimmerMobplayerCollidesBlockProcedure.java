package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.potion.ShimmeringPotionEffect;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.warcar.terrariareference.ModRecipeTypes;
import net.minecraft.inventory.Inventory;
import net.warcar.terrariareference.ShimmerTransformation;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Optional;

public class ShimmerMobplayerCollidesBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency world for procedure ShimmerMobplayerCollidesBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency x for procedure ShimmerMobplayerCollidesBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency y for procedure ShimmerMobplayerCollidesBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency z for procedure ShimmerMobplayerCollidesBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency entity for procedure ShimmerMobplayerCollidesBlock!");
			return;
		}
		World world = (World) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.isRemote()){return;}
		if (!entity.getPersistentData().getBoolean("shimmerproof")) {
			if (entity instanceof PlayerEntity) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(ShimmeringPotionEffect.potion, 1000000, 0, false, false));
			}else if (entity instanceof ThrowableEntity || entity instanceof ArrowEntity) {
				entity.setMotion(entity.getMotion().getX(), Math.abs(entity.getMotion().getY()), entity.getMotion().getZ());
			}else if (entity instanceof ItemEntity) {
				ItemStack stack = (new Object() {
					public ItemStack entityToItem(Entity _ent) {
						if (_ent instanceof ItemEntity) {
							return ((ItemEntity) _ent).getItem();
						}
						return ItemStack.EMPTY;
					}
				}.entityToItem(entity));
				PlayerEntity _ent = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(x - (200 / 2d), y - (200 / 2d), z - (200 / 2d), x + (200 / 2d), y + (200 / 2d), z + (200 / 2d)), null).stream().sorted(new Object() {
					Comparator<PlayerEntity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<PlayerEntity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
				Map<String, Object> run = dependencies;
				run.put("itemstack", stack);
				run.put("random", _ent.getRNG());
				AtomicBoolean transformation = new AtomicBoolean(false);
				Inventory inv = new Inventory(1);
				inv.setInventorySlotContents(0, stack);
				Optional<ShimmerTransformation> recipe = world.getRecipeManager().getRecipe(ModRecipeTypes.SHIMMER_TRANSFORMATION, inv, world);
				recipe.ifPresent(iRecipe ->{
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, iRecipe.getRecipeOutput());
					entityToSpawn.setPickupDelay((int) 10);
					entityToSpawn.setNoGravity(true);
					entityToSpawn.getPersistentData().putBoolean("shimmerproof", true);
					world.addEntity(entityToSpawn);
					transformation.set(true);
				});
				if(transformation.get()){return;}
				RevertCraftsProcedure.executeProcedure(run).ifPresent(itemsAndCount -> {
					boolean gaveIngredients = false;
					for (ItemStack ingredient : itemsAndCount.getLeft()) {
						if (!ingredient.isEmpty()) {
							ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, ingredient);
							entityToSpawn.setPickupDelay((int) 10);
							entityToSpawn.setNoGravity(true);
							entityToSpawn.getPersistentData().putBoolean("shimmerproof", true);
							world.addEntity(entityToSpawn);
							gaveIngredients = true;
						}
					}
					if (gaveIngredients) {
						stack.shrink(itemsAndCount.getRight());
					}
				});
			} else {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, (int) 1000000, (int) 0, (true), (false)));
			}
		}
	}
}
