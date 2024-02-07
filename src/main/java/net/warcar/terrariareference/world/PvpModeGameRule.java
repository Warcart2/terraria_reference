package net.warcar.terrariareference.world;

import net.warcar.terrariareference.TerrariaReferenceModElements;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import java.lang.reflect.Method;

@TerrariaReferenceModElements.ModElement.Tag
public class PvpModeGameRule extends TerrariaReferenceModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("pvpMode", GameRules.Category.PLAYER, create(false));

	public PvpModeGameRule(TerrariaReferenceModElements instance) {
		super(instance, 213);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
