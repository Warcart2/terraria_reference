package net.warcar.terrariareference.procedures;

import net.warcar.terrariareference.block.EbonwoodPlanksBlock;
import net.warcar.terrariareference.block.EbonwoodLogBlock;
import net.warcar.terrariareference.block.EbonstoneBlock;
import net.warcar.terrariareference.block.CorruptionGrassBlock;
import net.warcar.terrariareference.TerrariaReferenceMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;

public class CorruptionVersionProcedure {

	public static BlockState executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("blockstate") == null) {
			if (!dependencies.containsKey("blockstate"))
				TerrariaReferenceMod.LOGGER.warn("Failed to load dependency blockstate for procedure CorruptionVersion!");
			return Blocks.AIR.getDefaultState();
		}
		BlockState blockstate = (BlockState) dependencies.get("blockstate");
		if (BlockTags.getCollection().getTagByID(new ResourceLocation("terraria_reference:corruption")).contains(blockstate.getBlock())
				|| BlockTags.getCollection().getTagByID(new ResourceLocation("terraria_reference:crimson")).contains(blockstate.getBlock())) {
			return blockstate;
		}
		if (blockstate.getMaterial() == net.minecraft.block.material.Material.EARTH || blockstate.getMaterial() == net.minecraft.block.material.Material.ORGANIC) {
			return CorruptionGrassBlock.block.getDefaultState();
		} else if (!BlockTags.getCollection().getTagByID(new ResourceLocation("forge:ores")).contains(blockstate.getBlock()) && blockstate.getMaterial() == net.minecraft.block.material.Material.ROCK) {
			return EbonstoneBlock.block.getDefaultState();
		} else if (BlockTags.getCollection().getTagByID(new ResourceLocation("minecraft:logs")).contains(blockstate.getBlock())) {
			return EbonwoodLogBlock.block.getDefaultState();
		} else if (BlockTags.getCollection().getTagByID(new ResourceLocation("minecraft:planks")).contains(blockstate.getBlock())) {
			return EbonwoodPlanksBlock.block.getDefaultState();
		}
		return blockstate;
	}
}
