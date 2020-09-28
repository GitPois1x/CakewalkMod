package net.cakewalk.block.entity;

import net.cakewalk.init.BlockInit;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class PylonBaseBlockEntity extends BlockEntity implements Tickable, Inventory, BlockEntityClientSerializable {
  private DefaultedList<ItemStack> inventory;
  public int teleportTimer = 0;

  public PylonBaseBlockEntity() {
    super(BlockInit.PYLON_BASE_BLOCK_ENTITY);
    this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
  }

  @Override
  public void tick() {
    if (!this.inventory.isEmpty()) {
      if (this.teleportTimer > 0) {
        if (this.world.isClient) {
          double d = (double) pos.getX() + 0.5D + ((double) world.random.nextFloat() / 8);
          double e = (double) pos.getY() + 0.2D + ((double) world.random.nextFloat() / 8);
          double f = (double) pos.getZ() + 0.5D + ((double) world.random.nextFloat() / 8);
          this.world.addParticle(ParticleTypes.FIREWORK, d, e, f, (world.random.nextDouble() - 0.5D) / 2.0D,
              -world.random.nextDouble(), (world.random.nextDouble() - 0.5D) / 2.0D);
        }
        this.teleportTimer--;
      }
    }
  }

  @Override
  public void clear() {
    this.inventory.clear();
    this.markDirty();
  }

  @Override
  public void fromClientTag(CompoundTag tag) {
    inventory.clear();
    Inventories.fromTag(tag, inventory);
  }

  @Override
  public CompoundTag toClientTag(CompoundTag tag) {
    super.toTag(tag);
    Inventories.toTag(tag, inventory);
    return tag;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean isEmpty() {
    return this.getStack(0).isEmpty();
  }

  @Override
  public ItemStack getStack(int slot) {
    return this.inventory.get(0);
  }

  @Override
  public ItemStack removeStack(int slot, int amount) {
    ItemStack result = Inventories.splitStack(this.inventory, slot, 1);
    this.markDirty();
    return result;
  }

  @Override
  public ItemStack removeStack(int slot) {
    this.markDirty();
    return Inventories.removeStack(this.inventory, slot);
  }

  @Override
  public void setStack(int slot, ItemStack stack) {
    this.inventory.set(0, stack);
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
      this.world.updateListeners(this.pos, state, state, 3);
    }
  }

  @Override
  public void fromTag(BlockState state, CompoundTag tag) {
    super.fromTag(state, tag);
    inventory.clear();
    Inventories.fromTag(tag, inventory);
  }

  @Override
  public CompoundTag toTag(CompoundTag tag) {
    super.toTag(tag);
    Inventories.toTag(tag, inventory);
    return tag;
  }

}
