package net.cakewalk;

import net.cakewalk.init.*;
import net.fabricmc.api.ModInitializer;

public class CakeWalkMain implements ModInitializer {

  @Override
  public void onInitialize() {
    BlockInit.init();
    EntityInit.init();
    ItemInit.init();
    GenInit.init();
    TagInit.init();
    SpawnInit.init();
  }

}
