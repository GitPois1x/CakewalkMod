package net.cakewalk.item;

import net.cakewalk.access.RadiationManagerAccess;
import net.cakewalk.manager.RadiationManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RadAwayItem extends Item {

  public RadAwayItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    RadiationManager radiationManager = ((RadiationManagerAccess) user).getRadiationManager(user);
    ItemStack itemStack = user.getStackInHand(hand);
    if (!user.abilities.creativeMode && radiationManager.getRadiationLevel() >= 0) {
      radiationManager.setRadiationLevel(0);
      user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 0, false, false));
      user.setCurrentHand(hand);
      user.getStackInHand(hand).decrement(1);
      return TypedActionResult.success(itemStack);
    } else {
      return TypedActionResult.pass(itemStack);
    }
  }

}
