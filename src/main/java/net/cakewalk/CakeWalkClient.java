package net.cakewalk;

import net.cakewalk.init.RenderInit;
import net.cakewalk.network.RadiationS2CPacket;
import net.cakewalk.network.SyncPacket;
import net.fabricmc.api.ClientModInitializer;

public class CakeWalkClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    RenderInit.init();
    SyncPacket.init();
    RadiationS2CPacket.init();
  }

}
