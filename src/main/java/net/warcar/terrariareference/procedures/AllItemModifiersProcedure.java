package net.warcar.terrariareference.procedures;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attribute;
import net.warcar.terrariareference.init.TerrariaReferenceModAttributes;

import net.warcar.terrariareference.ItemModifier;
import net.warcar.terrariareference.TerrariaReferenceMod;

import java.util.UUID;
import java.util.HashMap;
import java.util.Random;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.NewRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.util.ResourceLocation;
import java.util.concurrent.atomic.AtomicReference;

public class AllItemModifiersProcedure {
	@Mod.EventBusSubscriber
	private static class Reg {
		@SubscribeEvent
		public static void registryCreating(RegistryEvent.NewRegistry event){
			RegistryBuilder itemModifiers = new RegistryBuilder();
			itemModifiers.setType(ItemModifier.RegistryEntry.class);
			itemModifiers.tagFolder("item_modifiers");
			itemModifiers.create();
		}
		@SubscribeEvent
		public static void registring(RegistryEvent.Register<ItemModifier.RegistryEntry> event){
			Object[] arr = All.keySet().toArray();
			for (Object i : arr) {
				String str = (String) i;
				event.getRegistry().register(new ItemModifier.RegistryEntry(All.get(str), new ResourceLocation(TerrariaReferenceMod.MODID, str)));
			}
		}
	}
	private static HashMap<String, ItemModifier> melee() {
		String name;
		HashMap<String, ItemModifier> out = new HashMap<>();
		final HashMap<Attribute, AttributeModifier> mod = new HashMap<>();
		out.putAll(common());

		mod.clear();
		name = "large";
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.12, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "massive";
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.18, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "dangerous";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 2, AttributeModifier.Operation.ADDITION));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "savage";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "sharp";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "pointy";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "tiny";
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.18, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "terrible";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.13, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "small";
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "dull";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "unhappy";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "shameful";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "bulky";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "heavy";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "light";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod.clear();
		name = "legendary";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 5, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		return out;
	}
	private static HashMap<String, ItemModifier> common() {
		HashMap<String, ItemModifier> out = new HashMap<>();
		HashMap<Attribute, AttributeModifier> mod = new HashMap<>();
		String name;
		out.putAll(tool());

		mod = new HashMap<>();
		name = "quick";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "deadly";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "agile";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 3, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "nimble";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "murderous";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.07, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.06, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 3, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "slow";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "sluggish";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "lazy";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.08, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "annoying";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "nasty";
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 3, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		return out;
	}

	private static HashMap<String, ItemModifier> tool() {
		HashMap<String, ItemModifier> out = new HashMap<>();
		HashMap<Attribute, AttributeModifier> mod = new HashMap<>();
		String name;

		mod = new HashMap<>();
		name = "broken";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "superior";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 3, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "forceful";
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "damaged";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "shoddy";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "hurtful";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "strong";
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "unpleasant";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.05, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "weak";
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "ruthless";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.18, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "demonic";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 5, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "godly";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("61d01730-7577-4dca-be36-e3f23fba7eaf"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 5, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "zealous";
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 5, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		mod = new HashMap<>();
		name = "keen";
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 3, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod));

		return out;
	}
	private static HashMap<String, ItemModifier> all() {
		HashMap<String, ItemModifier> out = new HashMap<>();
		HashMap<Attribute, AttributeModifier> mod = new HashMap<>();
		String name;
		out.putAll(melee());
		out.putAll(common());
		out.putAll(tool());

		mod.clear();
		name = "dev";
		mod.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("7c3d51b9-56c3-4cc7-81cb-9feeebb30c9b"), name, 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("7c3d51b9-56c3-4cc7-81cb-9feeebb30c9b"), name, 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("7c3d51b9-56c3-4cc7-81cb-9feeebb30c9b"), name, 100, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("7c3d51b9-56c3-4cc7-81cb-9feeebb30c9b"), name, 10, AttributeModifier.Operation.MULTIPLY_TOTAL));
		mod.put(TerrariaReferenceModAttributes.CRITICAL_STRIKE_CHANCE, new AttributeModifier(UUID.fromString("087a9957-5994-4d3b-97f8-22b4566cf3a3"), name, 100, AttributeModifier.Operation.ADDITION));
		out.put(name, new ItemModifier(mod, true));

		return out;
	}
	public static final HashMap<String, ItemModifier> All = all();
	public static final HashMap<String, ItemModifier> Tool = tool();
	public static final HashMap<String, ItemModifier> Common = common();
	public static final HashMap<String, ItemModifier> Melee = melee();
}
