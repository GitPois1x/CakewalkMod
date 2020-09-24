package net.cakewalk.item;

import net.cakewalk.init.BlockInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WarpStoneItem extends Item {

  public WarpStoneItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext ctx) {
    PlayerEntity player = ctx.getPlayer();

    if (player.isSneaking()
        && ctx.getWorld().getBlockState(new BlockPos(ctx.getHitPos())).isOf(BlockInit.PYLON_BASE_BLOCK)) {
      ItemStack stack = ctx.getStack();
      CompoundTag tags = stack.getTag();
      if (tags == null) {
        stack.setTag(new CompoundTag());
        tags = stack.getTag();
      }

      BlockPos offPos = ctx.getBlockPos().offset(ctx.getSide());
      tags.putInt("position_x", offPos.getX());
      tags.putInt("position_y", offPos.getY());
      tags.putInt("position_z", offPos.getZ());
      tags.putString("player_dimension", player.world.getDimension().toString());// player.world.getDimensionRegistryKey().getValue().toString());
      tags.putFloat("player_direction", player.yaw);

      TranslatableText msg = new TranslatableText("text.teleporters.crystal_info", offPos.getX(), offPos.getY(),
          offPos.getZ());
      msg.setStyle(Style.EMPTY.withColor(Formatting.DARK_PURPLE));

      player.sendMessage(msg, true);

      player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.5F,
          0.4F / (player.world.random.nextFloat() * 0.4F + 0.8F));
      return ActionResult.PASS;
    }
    return ActionResult.PASS;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack stack = user.getStackInHand(hand);
    CompoundTag tags = stack.getTag();
    if (tags != null && !user.isSneaking()) {
      double destX = (double) tags.getInt("position_x");
      double destY = (double) tags.getInt("position_y");
      double destZ = (double) tags.getInt("position_z");
      float player_yaw = tags.getFloat("player_direction");
      if (!world.isClient) {
        user.teleport(destX, destY, destZ);
        // user.refreshPositionAndAngles(pos, yaw, pitch);
        user.refreshPositionAndAngles(destX, destY, destZ, player_yaw, 1.0F);
      }
      user.setCurrentHand(hand);
      return TypedActionResult.consume(stack);
    } else
      return TypedActionResult.pass(user.getStackInHand(hand));
    // if (this.isFood()) {
    // ItemStack itemStack = user.getStackInHand(hand);
    // if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
    // user.setCurrentHand(hand);
    // return TypedActionResult.consume(itemStack);
    // } else {
    // return TypedActionResult.fail(itemStack);
    // }
    // } else {
    // return TypedActionResult.pass(user.getStackInHand(hand));
    // }
  }

}
