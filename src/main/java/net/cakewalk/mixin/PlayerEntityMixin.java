package net.cakewalk.mixin;

import java.util.Objects;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.cakewalk.access.RadiationManagerAccess;
import net.cakewalk.init.ItemInit;
import net.cakewalk.manager.RadiationManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements RadiationManagerAccess {
  private RadiationManager radiationManager = new RadiationManager();
  private int radiationTimer;

  @Override
  public RadiationManager getRadiationManager(PlayerEntity player) {
    return this.radiationManager;
  }

  public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(method = "checkFallFlying", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/player/PlayerEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), cancellable = true)
  public void checkFallFlyingMixin(CallbackInfoReturnable<Boolean> info) {
    ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
    if (itemStack.getItem() == ItemInit.DRAGON_CHESTPLATE) {
      this.startFallFlying();
      info.setReturnValue(true);
    }

  }

  @Inject(method = "Lnet/minecraft/entity/player/PlayerEntity;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;update(Lnet/minecraft/entity/player/PlayerEntity;)V", shift = Shift.AFTER))
  private void tickMixinTwo(CallbackInfo info) {
    Object object = this;
    PlayerEntity player = (PlayerEntity) object;
    this.radiationManager.update(player);
  }

  @Inject(method = "Lnet/minecraft/entity/player/PlayerEntity;tickMovement()V", at = @At(value = "HEAD"))
  private void tickMovementMixin(CallbackInfo info) {
    PlayerEntity player = (PlayerEntity) (Object) this;
    if (!player.isCreative()) {
      this.radiationManager.update(player);
      Optional<RegistryKey<Biome>> optional = world.method_31081(this.getBlockPos());
      if (this.radiationManager.isNotEmpty() && this.age % 10 == 0
          && !Objects.equals(optional, Optional.of(BiomeKeys.BASALT_DELTAS))) {
        this.radiationManager.setRadiationLevel(this.radiationManager.getRadiationLevel() - 1);
      }
      if (Objects.equals(optional, Optional.of(BiomeKeys.BASALT_DELTAS)) && wearsArmorModifier(player) != 480) {
        radiationTimer++;
        if (radiationTimer % (600 + wearsArmorModifier(player)) == 0) {
          radiationManager.add(1);
        }
      } else if (radiationTimer > 0) {
        radiationTimer = 0;
      }
    }
  }

  @Inject(method = "Lnet/minecraft/entity/player/PlayerEntity;readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
  private void readCustomDataFromTagMixin(CompoundTag tag, CallbackInfo info) {
    this.radiationManager.fromTag(tag);
  }

  @Inject(method = "Lnet/minecraft/entity/player/PlayerEntity;writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
  private void writeCustomDataToTagMixin(CompoundTag tag, CallbackInfo info) {
    this.radiationManager.toTag(tag);
  }

  @Inject(method = "canFoodHeal", at = @At(value = "HEAD"), cancellable = true)
  public void canFoodHealMixin(CallbackInfoReturnable<Boolean> info) {
    if (this.radiationManager.getRadiationLevel() > 14) {
      info.setReturnValue(false);
    }
  }

  @ModifyArg(method = "Lnet/minecraft/entity/player/PlayerEntity;tickMovement()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;heal(F)V"))
  private float tickMovementStopRegenMixin(float f) {
    if (this.radiationManager.getRadiationLevel() > 4) {
      return 0.0F;
    } else
      return f;
  }

  @Shadow
  public void startFallFlying() {
  }

  private static int wearsArmorModifier(LivingEntity livingEntity) {
    int leadModifier = 0;
    int wearsArmorModifier = 120;
    ItemStack headStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
    ItemStack chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    ItemStack legStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
    ItemStack feetStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);
    if (headStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.GOLDEN_LEAD_LINED_HELMET))
        || headStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.LEAD_LINED_HELMET))) {
      leadModifier = leadModifier + wearsArmorModifier;
    }
    if (chestStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.GOLDEN_LEAD_LINED_CHESTPLATE))
        || chestStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.LEAD_LINED_CHESTPLATE))) {
      leadModifier = leadModifier + wearsArmorModifier;
    }
    if (legStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.GOLDEN_LEAD_LINED_LEGGINGS))
        || legStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.LEAD_LINED_LEGGINGS))) {
      leadModifier = leadModifier + wearsArmorModifier;
    }
    if (feetStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.GOLDEN_LEAD_LINED_BOOTS))
        || feetStack.isItemEqualIgnoreDamage(new ItemStack(ItemInit.LEAD_LINED_BOOTS))) {
      leadModifier = leadModifier + wearsArmorModifier;
    }
    return leadModifier;
  }

}
