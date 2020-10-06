package com.mechanicraft.base;

import com.mechanicraft.base.block.DirectionalStoneBlock;
import com.mechanicraft.base.block.PlateBlock;
import com.mechanicraft.base.block.entity.PlateBlockEntity;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Mechanicraft implements ModInitializer {

	public static final String MOD_ID = "mechanicraft";

	public static final Identifier PLATE_ID = new Identifier(MOD_ID, "plate");

	public static BlockEntityType<PlateBlockEntity> PLATE_BLOCKENTITY;
	
	public static final Block PLATE_BLOCK = new PlateBlock();
	public static final Block DIRECTIONAL_STONE = new DirectionalStoneBlock();

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "directional_stone"), DIRECTIONAL_STONE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "directional_stone"), new BlockItem(DIRECTIONAL_STONE, new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.BLOCK, PLATE_ID, PLATE_BLOCK);
		Registry.register(Registry.ITEM, PLATE_ID, new BlockItem(PLATE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
		PLATE_BLOCKENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, PLATE_ID, BlockEntityType.Builder.create(PlateBlockEntity::new, PLATE_BLOCK).build(null));
	}
}
