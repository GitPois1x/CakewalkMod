package net.cakewalk.block.entity.render;

import net.cakewalk.block.entity.PylonBaseBlockEntity;
import net.cakewalk.init.ItemInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;

public class PylonBaseBlockEntityRenderer extends BlockEntityRenderer<PylonBaseBlockEntity> {

  public PylonBaseBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
    super(dispatcher);
  }

  @Override
  public void render(PylonBaseBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
      VertexConsumerProvider vertexConsumers, int light, int overlay) {
    if (!blockEntity.isEmpty()) {
      matrices.push();
      matrices.translate(0.5D, 0.55D, 0.5D);
      matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * 2F));
      int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
      MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ItemInit.WARP_STONE_PLACED_ITEM),
          ModelTransformation.Mode.GROUND, lightAbove, 10018511, matrices, vertexConsumers);
      matrices.pop();
    }
  }
}