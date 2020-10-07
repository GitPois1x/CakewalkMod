package net.cakewalk.init;

import net.cakewalk.block.entity.render.*;
import net.cakewalk.entity.render.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class RenderInit {

  public static void init() {
    // Block
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.PYLON_BASE_BLOCK, RenderLayer.getCutout());
    // Block Entity
    BlockEntityRendererRegistry.INSTANCE.register(BlockInit.PYLON_BASE_BLOCK_ENTITY, PylonBaseBlockEntityRenderer::new);
    // Living Entity
    EntityRendererRegistry.INSTANCE.register(EntityInit.DIRE_WOLF_ENTITY_TYPE,
        (dispatcher, context) -> new DireWolfRenderer(dispatcher));
  }

}
