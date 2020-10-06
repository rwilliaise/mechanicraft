package com.mechanicraft.base.block.entity;

import com.mechanicraft.base.MechanicraftRegistry;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class FireboxBlockEntity extends LootableContainerBlockEntity
		implements SidedInventory, BlockEntityClientSerializable {

	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

	public FireboxBlockEntity() {
		super(MechanicraftRegistry.FIREBOX_ENTITY);
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
		return false;
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
	public int[] getAvailableSlots(Direction side) {
		return null;
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	protected DefaultedList<ItemStack> getInvStackList() {
		return null;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> list) {

	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.firebox");
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return null;
	}
	
}
