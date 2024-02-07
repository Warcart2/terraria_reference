package net.warcar.terrariareference.procedures;

import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraft.item.crafting.ICraftingRecipe;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.world.World;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.Predicate;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.BlastingRecipe;

public class RevertCraftsProcedure {
	public static Optional<Pair<ItemStack[], Integer>> executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null){
			return Optional.empty();
		}
		if (dependencies.get("world") == null){
			return Optional.empty();
		}
		if (dependencies.get("random") == null){
			return Optional.empty();
		}
		World world = (World) dependencies.get("world");
		ItemStack item = (ItemStack) dependencies.get("itemstack");
		Random random = (Random) dependencies.get("random");
        return groupByPredicatesOrdered(
                world.getRecipeManager().getRecipes().stream(), Util.make(new ArrayList<>(), list -> {
                    list.add(recipe -> recipe instanceof SmithingRecipe);
                    list.add(recipe -> recipe instanceof AbstractCookingRecipe && !(recipe instanceof BlastingRecipe));
                    list.add(recipe -> recipe instanceof StonecuttingRecipe);
                    list.add(recipe -> recipe instanceof ICraftingRecipe);
                    list.add(recipe -> true);
                }), recipe -> outputMatches(recipe, item), false)
                .values().stream().filter(list -> !list.isEmpty()).findFirst()
                .flatMap(recipesOfPreferredType -> {
                    IRecipe<?> randomRecipe = recipesOfPreferredType.get(random.nextInt(recipesOfPreferredType.size()));
                    ItemStack[] ingredients = getIngredients(randomRecipe);
                    if (ingredients.length == 0) return Optional.empty();
                    return Optional.of(Pair.of(ingredients, randomRecipe.getRecipeOutput().getCount()));
                });
	}
	private static boolean outputMatches(IRecipe<?> recipe, ItemStack stack) {
        return recipe.getRecipeOutput().getItem() == stack.getItem() && recipe.getRecipeOutput().getCount() <= stack.getCount();
    }
   	private static <T> LinkedHashMap<Predicate<T>, List<T>> groupByPredicatesOrdered(Stream<T> elements, List<Predicate<T>> predicates, 
            @Nullable Predicate<T> commonCondition, boolean elementRepeats) {
        LinkedHashMap<Predicate<T>, List<T>> map = Util.make(new LinkedHashMap<>(), m -> {
            predicates.forEach(key -> m.put(key, new ArrayList<>()));
        });
        elements.forEach(element -> {
            if (commonCondition == null || commonCondition.test(element)) {
                for (Predicate<T> predicate : predicates) {
                    if (predicate.test(element)) {
                        map.get(predicate).add(element);
                        if (!elementRepeats) {
                            break;
                        }
                    }
                }
            }
        });
        return map;
    }
    private static ItemStack[] getIngredients(IRecipe<?> recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        ItemStack[] stacks = new ItemStack[ingredients.size()];

        Random random = new Random();
        for (int i = 0; i < ingredients.size(); i++) {
            ItemStack[] matchingStacks = ingredients.get(i).getMatchingStacks();
            stacks[i] = matchingStacks.length > 0 ? matchingStacks[random.nextInt(matchingStacks.length)].copy() : ItemStack.EMPTY;
        }

        return stacks;
    }
}
