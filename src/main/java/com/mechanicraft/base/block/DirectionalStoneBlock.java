package com.mechanicraft.base.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class DirectionalStoneBlock extends FacingBlock {

	public DirectionalStoneBlock() {
		super(FabricBlockSettings.of(Material.STONE));
		this.stateManager.getDefaultState().with(Properties.FACING, Direction.NORTH);
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.FACING);
	}

	@Override
	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		if (state.get(Properties.FACING).getOpposite() == direction) {
			return 15;
		}
		return 0;
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
	}
}
