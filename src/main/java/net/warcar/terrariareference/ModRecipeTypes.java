package net.warcar.terrariareference;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import net.warcar.terrariareference.TerrariaReferenceMod;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.util.registry.Registry;
import net.warcar.terrariareference.ShimmerTransformation;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TerrariaReferenceMod.MODID);

    public static final RegistryObject<ShimmerTransformation.Serializer> SHIMMER_SERIALIZER
            = RECIPE_SERIALIZER.register("shimmer_transformation", ShimmerTransformation.Serializer::new);

    public static IRecipeType<ShimmerTransformation> SHIMMER_TRANSFORMATION
            = new ShimmerTransformation.ShimmerTransformationType();


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, ShimmerTransformation.TYPE_ID, SHIMMER_TRANSFORMATION);
    }
}