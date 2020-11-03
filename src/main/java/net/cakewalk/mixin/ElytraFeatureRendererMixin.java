package net.cakewalk.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.cakewalk.init.ItemInit;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
    extends FeatureRenderer<T, M> {
  private static final Identifier DRAGON_ELYTRA_TEXTURE = new Identifier("cakewalk:textures/entity/dragon_elytra.png");
  private final ElytraEntityModel<T> elytraModel = new ElytraEntityModel<>();

  public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Inject(method = "render", at = @At("HEAD"), cancellable = true)
  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
      float f, float g, float h, float j, float k, float l, CallbackInfo info) {
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    if (itemStack.getItem() == ItemInit.DRAGON_CHESTPLATE) {
      Identifier identifier4;
      if (livingEntity instanceof AbstractClientPlayerEntity) {
        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity) livingEntity;
        if (abstractClientPlayerEntity.canRenderElytraTexture()
            && abstractClientPlayerEntity.getElytraTexture() != null) {
          identifier4 = abstractClientPlayerEntity.getElytraTexture();
        } else if (abstractClientPlayerEntity.canRenderCapeTexture()
            && abstractClientPlayerEntity.getCapeTexture() != null
            && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE)) {
          identifier4 = abstractClientPlayerEntity.getCapeTexture();
        } else {
          identifier4 = DRAGON_ELYTRA_TEXTURE;
        }
      } else {
        identifier4 = DRAGON_ELYTRA_TEXTURE;
      }

      matrixStack.push();
      matrixStack.translate(0.0D, 0.0D, 0.125D);
      this.getContextModel().copyStateTo(this.elytraModel);
      this.elytraModel.setAngles(livingEntity, f, g, j, k, l);
      VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
          RenderLayer.getArmorCutoutNoCull(identifier4), false, itemStack.hasGlint());
      this.elytraModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      matrixStack.pop();
      info.cancel();
    }
  }

}
