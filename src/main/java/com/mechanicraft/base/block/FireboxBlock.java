package com.mechanicraft.base.block;

import com.mechanicraft.base.block.entity.FireboxBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class FireboxBlock extends BlockWithEntity {

	public FireboxBlock() {
		super(FabricBlockSettings.of(Material.STONE));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new FireboxBlockEntity();
	}

}
