
package net.warcar.terrariareference.item;

import net.warcar.terrariareference.procedures.WaterWalkingBootsTickEventProcedure;
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
import net.minecraft.entity.LivingEntity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@TerrariaReferenceModElements.ModElement.Tag
public class WaterWalkingBootsItem extends TerrariaReferenceModElements.ModElement {
	@ObjectHolder("terraria_reference:water_walking_boots")
	public static final Item block = null;

	public WaterWalkingBootsItem(TerrariaReferenceModElements instance) {
		super(instance, 295);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item implements ICurioItem {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).rarity(Rarity.UNCOMMON));
			setRegistryName("water_walking_boots");
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
			WaterWalkingBootsTickEventProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
		}
	}
}
