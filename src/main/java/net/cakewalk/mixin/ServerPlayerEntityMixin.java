package net.cakewalk.mixin;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.buffer.Unpooled;

import org.spongepowered.asm.mixin.injection.At;

import net.cakewalk.access.RadiationManagerAccess;
import net.cakewalk.manager.RadiationManager;
import net.cakewalk.network.RadiationS2CPacket;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
  RadiationManager radiationManager = ((RadiationManagerAccess) this).getRadiationManager(this);
  private int syncedRadiationLevel = -99999999;

  public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
    super(world, pos, yaw, profile);
  }

  @Inject(method = "playerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;tick()V", shift = Shift.AFTER))
  public void playerTickMixin(CallbackInfo info) {
    if (this.syncedRadiationLevel != this.radiationManager.getRadiationLevel()) {
      PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
      data.writeIntArray(new int[] { this.getEntityId(), radiationManager.getRadiationLevel() });
      ServerSidePacketRegistry.INSTANCE.sendToPlayer(this, RadiationS2CPacket.RADIATON_UPDATE, data);
      this.syncedRadiationLevel = radiationManager.getRadiationLevel();
    }
  }

  @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;clone(Lnet/minecraft/entity/player/PlayerInventory;)V", shift = Shift.AFTER))
  public void copyFromMixinOne(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
    this.radiationManager = ((RadiationManagerAccess) oldPlayer).getRadiationManager(oldPlayer);
  }

  @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "TAIL"))
  public void copyFromMixinTwo(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
    this.syncedRadiationLevel = -1;
  }

}
