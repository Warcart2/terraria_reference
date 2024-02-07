package net.warcar.terrariareference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;
import com.mojang.realmsclient.util.JsonUtils;
import com.google.gson.JsonArray;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.crafting.IRecipeType;
import com.google.gson.JsonObject;
import net.warcar.terrariareference.ModRecipeTypes;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.JSONUtils;
import javax.annotation.Nullable;
import net.minecraft.item.Item;


public class ShimmerTransformation implements IShimmerTransformation {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public ShimmerTransformation(ResourceLocation id, ItemStack output,
                                    NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return recipeItems.get(0).test(inv.getStackInSlot(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    public ItemStack getIcon() {
        return new ItemStack(null);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.SHIMMER_SERIALIZER.get();
    }

    public static class ShimmerTransformationType implements IRecipeType<ShimmerTransformation> {
        @Override
        public String toString() {
            return ShimmerTransformation.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<ShimmerTransformation> {

        @Override
        public ShimmerTransformation read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));

            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            inputs.set(0, Ingredient.deserialize(ingredients.get(0)));

            return new ShimmerTransformation(recipeId, output,
                    inputs);
        }

        @Nullable
        @Override
        public ShimmerTransformation read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
			inputs.set(0, Ingredient.read(buffer));

            ItemStack output = buffer.readItemStack();
            return new ShimmerTransformation(recipeId, output,
                    inputs);
        }

        @Override
        public void write(PacketBuffer buffer, ShimmerTransformation recipe) {
            buffer.writeInt(0);
            recipe.getIngredients().get(0).write(buffer);
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }
}