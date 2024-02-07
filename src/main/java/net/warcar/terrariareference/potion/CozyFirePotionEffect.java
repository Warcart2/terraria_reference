
package net.warcar.terrariareference.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.warcar.terrariareference.init.TerrariaReferenceModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CozyFirePotionEffect {
	@ObjectHolder("terraria_reference:cozy_fire")
	public static final Effect potion = null;

	@SubscribeEvent
	public static void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}

	public static class EffectCustom extends Effect {
		public EffectCustom() {
			super(EffectType.BENEFICIAL, -2181511);
			this.addAttributesModifier(TerrariaReferenceModAttributes.HP_REGEN, "61d01730-7577-4dea-be31-e3f23fba7eaf", 0.5, AttributeModifier.Operation.MULTIPLY_BASE);
			setRegistryName("cozy_fire");
		}

		@Override
		public String getName() {
			return "effect.cozy_fire";
		}

		@Override
		public boolean isBeneficial() {
			return true;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
