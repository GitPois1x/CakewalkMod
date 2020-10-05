package net.cakewalk.access;

import net.cakewalk.manager.RadiationManager;
import net.minecraft.entity.player.PlayerEntity;

public interface RadiationManagerAccess {
  public RadiationManager getRadiationManager(PlayerEntity player);
}
