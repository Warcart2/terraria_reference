
package net.warcar.terrariareference.item;

import net.warcar.terrariareference.TerrariaReferenceModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;
import net.warcar.terrariareference.MountItem;
import net.warcar.terrariareference.entity.TestMountEntEntity;


@TerrariaReferenceModElements.ModElement.Tag
public class TestMountItem extends TerrariaReferenceModElements.ModElement {
	@ObjectHolder("terraria_reference:test_mount")
	public static final Item block = null;

	public TestMountItem(TerrariaReferenceModElements instance) {
		super(instance, 332);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends MountItem {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.COMMON), TestMountEntEntity.entity);
			setRegistryName("test_mount");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
