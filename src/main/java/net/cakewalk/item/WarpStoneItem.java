package net.cakewalk.item;

import net.cakewalk.block.entity.PylonBaseBlockEntity;
import net.cakewalk.init.BlockInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.block.NetherPortalBlock;

public class WarpStoneItem extends Item {

  public WarpStoneItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    PlayerEntity player = context.getPlayer();

    if (player.isSneaking()
        && context.getWorld().getBlockState(new BlockPos(context.getHitPos())).isOf(BlockInit.PYLON_BASE_BLOCK)) {
      ItemStack stack = context.getStack();
      CompoundTag tags = stack.getTag();
      if (tags == null) {
        stack.setTag(new CompoundTag());
        tags = stack.getTag();
      }

      BlockPos blockPos = context.getBlockPos();
      tags.putInt("position_x", blockPos.getX());
      tags.putInt("position_y", blockPos.getY());
      tags.putInt("position_z", blockPos.getZ());
      if (player.world instanceof ServerWorld) {
        ServerWorld serverWorld = (ServerWorld) player.world;
        RegistryKey<World> registryKey = serverWorld.getRegistryKey();
        tags.putString("player_dimension", registryKey.toString());
      }
      tags.putFloat("player_direction", player.yaw);
      player.sendMessage(new TranslatableText("item.cakewalk.warp_stone_set_point"), true);
      return ActionResult.PASS;
    }
    return ActionResult.PASS;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack stack = user.getStackInHand(hand);
    CompoundTag tags = stack.getTag();
    if (tags != null && !user.isSneaking() && !user.getItemCooldownManager().isCoolingDown(stack.getItem())) {
      double destX = (double) tags.getInt("position_x");
      double destY = (double) tags.getInt("position_y");
      double destZ = (double) tags.getInt("position_z");
      float player_yaw = tags.getFloat("player_direction");
      String playerDim = tags.getString("player_dimension");
      BlockPos pos = new BlockPos(destX, destY, destZ);
      if (user.world instanceof ServerWorld) {
        ServerWorld serverWorld = (ServerWorld) user.world;
        RegistryKey<World> registryKey = serverWorld.getRegistryKey();
        MinecraftServer minecraftServer = serverWorld.getServer();
        ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
        BlockEntity entity = serverWorld2.getBlockEntity(pos);
        if (!world.isClient && entity != null && entity instanceof PylonBaseBlockEntity) {
          PylonBaseBlockEntity pylonBaseBlockEntity = (PylonBaseBlockEntity) entity;
          if (!pylonBaseBlockEntity.isEmpty()) {
            for (int i = -1; i < 2; i++) {
              for (int u = -1; u < 2; u++) {
                BlockPos blockPos = new BlockPos(destX + i, destY, destZ + u);
                if (world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.down()).isAir()) {
                  if (!playerDim.equals(registryKey.toString())) {
                    user.moveToWorld(serverWorld2);
                  }
                  user.teleport(destX + i, destY, destZ + u);
                  user.refreshPositionAndAngles(destX + i, destY, destZ + u, player_yaw, 1.0F);
                  user.getItemCooldownManager().set(this, 2400);
                  break;
                }
              }

            }
          }
          user.setCurrentHand(hand);
          return TypedActionResult.consume(stack);
        }
      }
    }
    return TypedActionResult.pass(user.getStackInHand(hand));

  }
}
