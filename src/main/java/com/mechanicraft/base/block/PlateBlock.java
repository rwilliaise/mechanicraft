package com.mechanicraft.base.block;

import com.mechanicraft.base.block.entity.PlateBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PlateBlock extends Block implements BlockEntityProvider {


	private static final VoxelShape SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);;

	public PlateBlock() {
		super(FabricBlockSettings.of(Material.STONE));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new PlateBlockEntity();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBreak(world, pos, state, player);
		Inventory inv = (Inventory) world.getBlockEntity(pos);
		for (int i = 0; i < inv.size(); i++) {
			ItemStack stack = inv.getStack(i);
			ItemEntity entity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(entity);
		}
	}
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hit) {
		if (world.isClient) return ActionResult.PASS;
		PlateBlockEntity inv = (PlateBlockEntity) world.getBlockEntity(pos);

		if (inv.cooldown != 0) return ActionResult.PASS;
		inv.cooldown = 5;
		ItemStack playerStack = player.getStackInHand(hand);

		if (!playerStack.isEmpty() 
		  && playerStack.isFood()) {
			boolean filled = false;
			for (int i = 0; i < inv.size(); i++) {
				ItemStack stack = inv.getStack(i);
				if (stack.isEmpty()) {
					filled = true;
					inv.setStack(i, playerStack.copy());
					playerStack.setCount(0);
					break;
				} else if (stack.isItemEqual(playerStack)) {
					int count = stack.getCount();
					if (count == stack.getMaxCount()) {
						continue;
					} else if (count + playerStack.getCount() > stack.getMaxCount()) {
						filled = true;
						stack.setCount(stack.getMaxCount());
						playerStack.decrement(stack.getMaxCount() - count);
						break;
					} else {
						filled = true;
						stack.increment(playerStack.getCount());
						playerStack.decrement(count);
						break;
					}
				} else {
					continue;
				}
			}
			return filled ? ActionResult.CONSUME : ActionResult.PASS;
		} else if (playerStack.isEmpty()) {
			for (int i = inv.size() - 1; i >= 0; i--) {
				ItemStack stack = inv.getStack(i);
				if (stack.isEmpty()) { continue; }
				player.inventory.offerOrDrop(world, inv.getStack(i));
				inv.removeStack(i);
				break;
			}
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}
