package net.cakewalk.network;

import net.cakewalk.access.DragonAnvilInterface;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.util.Identifier;

public class SyncPacket {
  public static final Identifier ANVIL_SYNC_PACKET = new Identifier("backslot", "visibility_update");

  public static void init() {
    ClientSidePacketRegistry.INSTANCE.register(ANVIL_SYNC_PACKET, (context, buffer) -> {
      int entityId = buffer.readInt();
      String blockString = buffer.readString();
      context.getTaskQueue().execute(() -> {
        if (context.getPlayer().world.getEntityById(entityId) != null) {
          PlayerEntity player = (PlayerEntity) context.getPlayer().world.getEntityById(entityId);
          if (player.currentScreenHandler instanceof AnvilScreenHandler) {
            ((DragonAnvilInterface) player.currentScreenHandler).setDragonAnvil(blockString);
          }

        }
      });
    });
  }

}
