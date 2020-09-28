package net.cakewalk.block;

import net.cakewalk.block.entity.PylonBaseBlockEntity;
import net.cakewalk.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PylonBaseBlock extends Block implements BlockEntityProvider {
  private static final VoxelShape SHAPE;
  public static final DirectionProperty FACING;

  public PylonBaseBlock(Settings settings) {
    super(settings);
    this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
  }

  @Override
  public BlockEntity createBlockEntity(BlockView world) {
    return new PylonBaseBlockEntity();
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
    ItemStack stack = blockEntity.getStack(0);
    if (!stack.isEmpty()) {
      if (player.isSneaking()) {
        if (!world.isClient) {
          player.giveItemStack(stack);
          blockEntity.clear();
        }
      } else {
        PylonBaseBlockEntity pylonblockentity = (PylonBaseBlockEntity) world.getBlockEntity(pos);
        CompoundTag tags = stack.getTag();
        if (pylonblockentity.teleportTimer <= 0) {
          if (tags != null) {
            double destX = (double) tags.getInt("position_x");
            double destY = (double) tags.getInt("position_y");
            double destZ = (double) tags.getInt("position_z");
            pylonblockentity.teleportTimer = 200;
            player.teleport(destX, destY, destZ);
          } else {
            player.sendMessage(new TranslatableText("text.cakewalk.teleport_missing"), true);
          }
        } else {
          player.sendMessage(new TranslatableText("text.cakewalk.teleport_cooldown"), true);
        }
      }

      return ActionResult.SUCCESS;
    } else {
      ItemStack heldItem = player.getMainHandStack();
      if (heldItem.isItemEqual(new ItemStack(ItemInit.WARP_STONE_ITEM))) {
        if (!world.isClient) {
          blockEntity.setStack(0, heldItem.split(1));
          //heldItem.decrement(1);
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.SUCCESS;
        }

      }
    }
    return ActionResult.PASS;
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    return false;
  }

  @Override
  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (!state.isOf(newState.getBlock())) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof Inventory) {
        Inventory inventory = (Inventory) blockEntity;
        if (!inventory.isEmpty()) {
          ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
          world.updateComparators(pos, this);
        }

      }
      super.onStateReplaced(state, world, pos, newState, moved);
    }
  }

  static {
    FACING = HorizontalFacingBlock.FACING;
    SHAPE = Block.createCuboidShape(1D, 0D, 1D, 15D, 5D, 15D);
  }

}