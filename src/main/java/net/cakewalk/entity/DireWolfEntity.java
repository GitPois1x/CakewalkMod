package net.cakewalk.entity;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import com.google.common.collect.UnmodifiableIterator;

import net.cakewalk.init.EntityInit;
import net.minecraft.entity.Dismounting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DireWolfEntity extends WolfEntity {

  public DireWolfEntity(EntityType<? extends WolfEntity> entityType, World world) {
    super(entityType, world);
    this.stepHeight = 1.0F;
  }

  public static DefaultAttributeContainer.Builder createDireWolfAttributes() {
    return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
  }

  @Override
  public void travel(Vec3d movementInput) {
    if (this.isAlive()) {
      if (this.hasPassengers() && this.canBeControlledByRider()) {
        LivingEntity livingEntity = (LivingEntity) this.getPrimaryPassenger();
        this.yaw = livingEntity.yaw;
        this.prevYaw = this.yaw;
        this.pitch = livingEntity.pitch * 0.5F;
        this.setRotation(this.yaw, this.pitch);
        this.bodyYaw = this.yaw;
        this.headYaw = this.bodyYaw;
        float f = livingEntity.sidewaysSpeed * 0.4F;
        float g = livingEntity.forwardSpeed * 0.5F;
        this.flyingSpeed = this.getMovementSpeed();
        if (this.isLogicalSideForUpdatingMovement()) {
          this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
          super.travel(new Vec3d((double) f, movementInput.y, (double) g));
        } else if (livingEntity instanceof PlayerEntity) {
          this.setVelocity(Vec3d.ZERO);
        }
        this.method_29242(this, false);
      } else {
        this.flyingSpeed = 0.02F;
        super.travel(movementInput);
      }
    }
  }

  @Override
  public boolean canBeControlledByRider() {
    return this.getPrimaryPassenger() instanceof LivingEntity;
  }

  @Override
  public void updatePassengerPosition(Entity passenger) {
    super.updatePassengerPosition(passenger);
    if (passenger instanceof MobEntity) {
      MobEntity mobEntity = (MobEntity) passenger;
      this.bodyYaw = mobEntity.bodyYaw;
    }
  }

  @Override
  public boolean isClimbing() {
    return false;
  }

  @Override
  @Nullable
  public Entity getPrimaryPassenger() {
    return this.getPassengerList().isEmpty() ? null : (Entity) this.getPassengerList().get(0);
  }

  @Nullable
  private Vec3d method_27930(Vec3d vec3d, LivingEntity livingEntity) {
    double d = this.getX() + vec3d.x;
    double e = this.getBoundingBox().minY;
    double f = this.getZ() + vec3d.z;
    BlockPos.Mutable mutable = new BlockPos.Mutable();
    UnmodifiableIterator<EntityPose> var10 = livingEntity.getPoses().iterator();

    while (var10.hasNext()) {
      EntityPose entityPose = (EntityPose) var10.next();
      mutable.set(d, e, f);
      double g = this.getBoundingBox().maxY + 0.75D;

      while (true) {
        double h = this.world.getDismountHeight(mutable);
        if ((double) mutable.getY() + h > g) {
          break;
        }

        if (Dismounting.canDismountInBlock(h)) {
          Box box = livingEntity.getBoundingBox(entityPose);
          Vec3d vec3d2 = new Vec3d(d, (double) mutable.getY() + h, f);
          if (Dismounting.canPlaceEntityAt(this.world, livingEntity, box.offset(vec3d2))) {
            livingEntity.setPose(entityPose);
            return vec3d2;
          }
        }

        mutable.move(Direction.UP);
        if ((double) mutable.getY() >= g) {
          break;
        }
      }
    }

    return null;
  }

  @Override
  public Vec3d updatePassengerForDismount(LivingEntity passenger) {
    Vec3d vec3d = getPassengerDismountOffset((double) this.getWidth(), (double) passenger.getWidth(),
        this.yaw + (passenger.getMainArm() == Arm.RIGHT ? 90.0F : -90.0F));
    Vec3d vec3d2 = this.method_27930(vec3d, passenger);
    if (vec3d2 != null) {
      return vec3d2;
    } else {
      Vec3d vec3d3 = getPassengerDismountOffset((double) this.getWidth(), (double) passenger.getWidth(),
          this.yaw + (passenger.getMainArm() == Arm.LEFT ? 90.0F : -90.0F));
      Vec3d vec3d4 = this.method_27930(vec3d3, passenger);
      return vec3d4 != null ? vec3d4 : this.getPos();
    }
  }

  @Override
  public int getLimitPerChunk() {
    return 3;
  }

  public void putPlayerOnBack(PlayerEntity player) {
    if (!this.world.isClient) {
      player.yaw = this.yaw;
      player.pitch = this.pitch;
      player.startRiding(this);
      System.out.print(this.canAddPassenger(player));
    }

  }

  @Override
  public ActionResult interactMob(PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getStackInHand(hand);
    Item item = itemStack.getItem();
    if (!this.isBaby()) {
      if (this.hasPassengers()) {
        return super.interactMob(player, hand);
      }
    }
    if (this.world.isClient) {
      boolean bl = this.isOwner(player) || this.isTamed()
          || item == Items.BONE && !this.isTamed() && !this.hasAngerTime();
      return bl ? ActionResult.CONSUME : ActionResult.PASS;
    } else {
      if (this.isTamed()) {
        if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth() && player.isSneaking()) {
          if (!player.abilities.creativeMode) {
            itemStack.decrement(1);
          }
          this.heal((float) item.getFoodComponent().getHunger());
          return ActionResult.SUCCESS;
        } else if (this.getHealth() > (this.getMaxHealth() / 2) && !player.isSneaking()) {
          this.setSitting(false);
          this.putPlayerOnBack(player);
          return ActionResult.SUCCESS;
        }
      } else if (item == Items.BONE && !this.hasAngerTime()) {
        if (!player.abilities.creativeMode) {
          itemStack.decrement(1);
        }

        if (this.random.nextInt(8) == 0) {
          this.setOwner(player);
          this.navigation.stop();
          this.setTarget((LivingEntity) null);
          this.world.sendEntityStatus(this, (byte) 7);
        } else {
          this.world.sendEntityStatus(this, (byte) 6);
        }

        return ActionResult.SUCCESS;
      }

      return super.interactMob(player, hand);
    }
  }

  @Override
  public boolean isPushable() {
    return !this.hasPassengers();
  }

  @Override
  public DireWolfEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
    DireWolfEntity wolfEntity = (DireWolfEntity) EntityInit.DIRE_WOLF_ENTITY_TYPE.create(serverWorld);
    UUID uUID = this.getOwnerUuid();
    if (uUID != null) {
      wolfEntity.setOwnerUuid(uUID);
      wolfEntity.setTamed(true);
    }

    return wolfEntity;
  }

}
