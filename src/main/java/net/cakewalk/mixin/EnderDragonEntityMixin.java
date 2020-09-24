package net.cakewalk.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.cakewalk.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends MobEntity {
  @Shadow
  public int ticksSinceDeath;

  public EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(method = "updatePostDeath", at = @At("TAIL"))
  public void updatePostDeathMixin(CallbackInfo info) {
    if (!this.world.isClient && this.ticksSinceDeath == 180) {
      for (int i = 0; i < 10; i++) {
        this.dropStack(new ItemStack(ItemInit.DRAGON_SCALE_ITEM));
      }

    }
  }

}