package com.spellcraft.base;

import java.util.function.Supplier;

import com.spellcraft.base.block.DirectionalStoneBlock;
import com.spellcraft.base.block.LaunchPadBlock;
import com.spellcraft.base.block.PlateBlock;
import com.spellcraft.base.block.entity.PlateBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellcraftRegistry {

	public static final Identifier PLATE_ID = new Identifier(Spellcraft.MOD_ID, "plate");
	public static final Identifier FIREBOX_ID = new Identifier(Spellcraft.MOD_ID, "firebox");

	public static BlockEntityType<PlateBlockEntity> PLATE_BLOCKENTITY;

	public static final Block PLATE_BLOCK = new PlateBlock();
	public static final Block LAUCH_PAD = new LaunchPadBlock();
	public static final Block DIRECTIONAL_STONE = new DirectionalStoneBlock();

	public static void initialize() {
		initializeBlock(DIRECTIONAL_STONE, "directional_stone");
		initializeBlock(PLATE_BLOCK, PLATE_ID);
		initializeBlock(LAUCH_PAD, "launch_pad");
		PLATE_BLOCKENTITY = initializeBlockEntity(PLATE_BLOCK, PLATE_ID, PlateBlockEntity::new);
	}

	private static void initializeBlock(Block block, String id) {
		Registry.register(Registry.BLOCK, new Identifier(Spellcraft.MOD_ID, id), block);
		Registry.register(Registry.ITEM, new Identifier(Spellcraft.MOD_ID, id), new BlockItem(block, new Item.Settings().group(ItemGroup.MISC)));
	}

	private static void initializeBlock(Block block, Identifier id) {
		Registry.register(Registry.BLOCK, id, block);
		Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(ItemGroup.MISC)));
	}

	// private static void initializeItem(Item item, String id) {
	// 	Registry.register(Registry.ITEM, new Identifier(Mechanicraft.MOD_ID, id), item);
	// }

	private static <T extends BlockEntity> BlockEntityType<T> initializeBlockEntity(Block block, Identifier id, Supplier<T> supplier) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.create(supplier, block).build(null));
	}
}
