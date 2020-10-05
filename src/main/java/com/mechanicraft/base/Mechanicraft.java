package com.mechanicraft.base;

import com.mechanicraft.base.block.DirectionalStoneBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Mechanicraft implements ModInitializer {

	public static final String MOD_ID = "mechanicraft";

	public static final Block DIRECTIONAL_STONE = new DirectionalStoneBlock();

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "directional_stone"),
				DIRECTIONAL_STONE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "directional_stone"),
				new BlockItem(DIRECTIONAL_STONE, new Item.Settings().group(ItemGroup.MISC)));
	}
}
