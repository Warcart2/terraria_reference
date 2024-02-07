package net.warcar.terrariareference;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;

public interface IShimmerTransformation extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(TerrariaReferenceMod.MODID, "shimmer_transformation");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }

    @Override
    default boolean isDynamic(){
        return true;
    }
}