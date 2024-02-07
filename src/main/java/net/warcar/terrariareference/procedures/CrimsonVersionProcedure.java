package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.block.ShadewoodPlanksBlock;
import net.warcar.terrariareference.block.ShadewoodLogBlock;
import net.warcar.terrariareference.block.CrimstoneBlock;
import net.warcar.terrariareference.block.CrimsonGrassBlock;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;

public class CrimsonVersionProcedure {

	public static BlockState executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("blockstate") == null) {
			if (!dependencies.containsKey("blockstate"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency blockstate for procedure CrimsonVersion!");
			return Blocks.AIR.getDefaultState();
		}
		BlockState blockstate = (BlockState) dependencies.get("blockstate");
		if (BlockTags.getCollection().getTagByID(new ResourceLocation("terraria_reference:corruption")).contains(blockstate.getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("terraria_reference:crimson")).contains(blockstate.getBlock())) {
			return blockstate;
		}
		if (blockstate.getMaterial() == net.minecraft.block.material.Material.ORGANIC || blockstate.getMaterial() == net.minecraft.block.material.Material.EARTH) {
			return CrimsonGrassBlock.block.getDefaultState();
		} else if (!BlockTags.getCollection().getTagByID(new ResourceLocation("forge:ores")).contains(blockstate.getBlock()) && blockstate.getMaterial() == net.minecraft.block.material.Material.ROCK) {
			return CrimstoneBlock.block.getDefaultState();
		} else if (BlockTags.getCollection().getTagByID(new ResourceLocation("minecraft:logs")).contains(blockstate.getBlock())) {
			return ShadewoodLogBlock.block.getDefaultState();
		} else if (BlockTags.getCollection().getTagByID(new ResourceLocation("minecraft:planks")).contains(blockstate.getBlock())) {
			return ShadewoodPlanksBlock.block.getDefaultState();
		}
		return blockstate;
	}
}
