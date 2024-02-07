
package net.warcar.terrariareference.item;

import net.warcar.terrariareference.procedures.LavaWadersTickEventProcedure;
import net.warcar.terrariareference.TerrariaReferenceModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraft.entity.LivingEntity;

@TerrariaReferenceModElements.ModElement.Tag
public class LavaWandersItem extends TerrariaReferenceModElements.ModElement {
	@ObjectHolder("terraria_reference:lava_waders")
	public static final Item block = null;

	public LavaWandersItem(TerrariaReferenceModElements instance) {
		super(instance, 293);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item implements ICurioItem {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("lava_waders");
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
			LavaWadersTickEventProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
