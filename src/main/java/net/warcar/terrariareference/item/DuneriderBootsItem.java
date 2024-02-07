
package net.warcar.terrariareference.item;

import net.warcar.terrariareference.TerrariaReferenceModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.IWorld;
import java.util.HashMap;
import net.warcar.terrariareference.procedures.DuneriderBootsTickEventProcedure;

@TerrariaReferenceModElements.ModElement.Tag
public class DuneriderBootsItem extends TerrariaReferenceModElements.ModElement {
	@ObjectHolder("terraria_reference:dunerider_boots")
	public static final Item block = null;

	public DuneriderBootsItem(TerrariaReferenceModElements instance) {
		super(instance, 291);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item implements ICurioItem {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).rarity(Rarity.COMMON));
			setRegistryName("dunerider_boots");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
		@Override
		public void curioTick(String id, int index, LivingEntity entity, ItemStack stack){
			HashMap<String, Object> run = new HashMap<>();
			run.put("x", entity.getPosX());
			run.put("y", entity.getPosY());
			run.put("z", entity.getPosZ());
			run.put("world", entity.world);
			run.put("itemstack", stack);
			DuneriderBootsTickEventProcedure.executeProcedure(run);
		}
	}
}
