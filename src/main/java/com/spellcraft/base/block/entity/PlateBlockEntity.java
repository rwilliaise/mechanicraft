package com.spellcraft.base.block.entity;

import com.spellcraft.base.SpellcraftRegistry;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class PlateBlockEntity extends BlockEntity implements Inventory, Tickable, BlockEntityClientSerializable {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

	public int cooldown = 0;

	public PlateBlockEntity() {
		super(SpellcraftRegistry.PLATE_BLOCKENTITY);
	}

	@Override
	public void tick() {
		if (cooldown > 0)
			cooldown--;
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		inventory.clear();
		Inventories.fromTag(tag, inventory);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		Inventories.toTag(tag, inventory);
		return super.toTag(tag);
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		fromTag(getCachedState(), tag);
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		return toTag(tag);
	}

	@Override
	public void clear() {
		this.inventory.clear();
	}

	@Override
	public int size() {
		return inventory.size();
	}

	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}

	@Override
	public ItemStack getStack(int slot) {
		return inventory.get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
		if (!result.isEmpty()) {
			markDirty();
		}
		return result;
	}

	@Override
	public ItemStack removeStack(int slot) {
		this.markDirty();
		return Inventories.removeStack(this.inventory, slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
		this.markDirty();
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return true;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		sendUpdate();
	}

	private void sendUpdate() {
		if (this.world != null) {
			BlockState state = this.world.getBlockState(this.pos);
			(this.world).updateListeners(this.pos, state, state, 3);
		}
	}
}
