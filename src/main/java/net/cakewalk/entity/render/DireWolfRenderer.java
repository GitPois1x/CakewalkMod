package net.cakewalk.entity.render;

import net.cakewalk.entity.DireWolfEntity;
import net.cakewalk.entity.model.DireWolfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DireWolfRenderer extends MobEntityRenderer<DireWolfEntity, DireWolfModel<DireWolfEntity>> {
  private static final Identifier TEXTURE = new Identifier("cakewalk:textures/entity/dire_wolf.png");

  public DireWolfRenderer(EntityRenderDispatcher entityRenderDispatcher) {
    super(entityRenderDispatcher, new DireWolfModel<>(), 0.7F);
  }

  @Override
  public void scale(DireWolfEntity wolfEntity, MatrixStack matrixStack, float f) {
    matrixStack.scale(1.1F, 1.1F, 1.1F);
  }

  @Override
  public Identifier getTexture(DireWolfEntity wolfEntity) {
    return TEXTURE;
  }
}
