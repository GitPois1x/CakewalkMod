package net.cakewalk;

import net.cakewalk.init.RenderInit;
import net.fabricmc.api.ClientModInitializer;

public class CakeWalkClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    RenderInit.init();
  }

}
