package net.cakewalk.network;

import net.cakewalk.access.RadiationManagerAccess;
import net.cakewalk.manager.RadiationManager;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class RadiationS2CPacket {
  public static final Identifier RADIATON_UPDATE = new Identifier("cakewalk", "radiation_update");

  public static void init() {
    ClientSidePacketRegistry.INSTANCE.register(RADIATON_UPDATE, (context, buffer) -> {
      int[] bufferArray = buffer.readIntArray();
      int entityId = bufferArray[0];
      int radiationLevel = bufferArray[1];
      context.getTaskQueue().execute(() -> {
        if (context.getPlayer().world.getEntityById(entityId) != null) {
          PlayerEntity player = (PlayerEntity) context.getPlayer().world.getEntityById(entityId);
          RadiationManager radiationManager = ((RadiationManagerAccess) player).getRadiationManager(player);
          radiationManager.setRadiationLevel(radiationLevel);
        }
      });
    });
  }

}