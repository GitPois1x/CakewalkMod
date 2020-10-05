package net.cakewalk.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.cakewalk.access.RadiationManagerAccess;
import net.cakewalk.manager.RadiationManager;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
  @Shadow
  @Final
  @Mutable
  private final MinecraftClient client;
  @Shadow
  private int ticks;
  @Shadow
  private int scaledWidth;
  @Shadow
  private int scaledHeight;

  private static final Identifier RADIATION_ICON = new Identifier("cakewalk:textures/misc/radiation_icon.png");

  public InGameHudMixin(MinecraftClient client) {
    this.client = client;
  }

  @Inject(method = "renderStatusBars", at = @At(value = "TAIL"))
  private void renderStatusBarsMixin(MatrixStack matrices, CallbackInfo info) {
    PlayerEntity playerEntity = this.getCameraPlayer();
    if (playerEntity != null) {
      RadiationManager radiationManager = ((RadiationManagerAccess) playerEntity).getRadiationManager(playerEntity);
      int radiation = radiationManager.getRadiationLevel();
      if (radiation > 0) {
        LivingEntity livingEntity = this.getRiddenEntity();
        int variable_one;
        int variable_two;
        int variable_three;
        int height = this.scaledHeight - 49;
        int width = this.scaledWidth / 2 + 91;
        if (this.getHeartCount(livingEntity) == 0) {
          for (variable_one = 0; variable_one < 10; ++variable_one) {
            variable_three = height;
            int airplayer = playerEntity.getAir();
            int airplayermax = playerEntity.getMaxAir();
            if (playerEntity.isSubmergedIn(FluidTags.WATER) || airplayer < airplayermax) {
              variable_three = variable_three - 10;
            }
            FabricLoader loader = FabricLoader.getInstance();
            if (loader.isModLoaded("dehydration")) {
              variable_three = variable_three - 10;
            }
            variable_two = width - variable_one * 8 - 9;
            this.client.getTextureManager().bindTexture(RADIATION_ICON);
            this.drawTexture(matrices, variable_two, variable_three, 0, 0, 9, 9);
            if (variable_one * 2 + 1 < radiation) {
              this.drawTexture(matrices, variable_two, variable_three, 9, 0, 9, 9); // Big icon
            }
            if (variable_one * 2 + 1 == radiation) {
              this.drawTexture(matrices, variable_two, variable_three, 18, 0, 9, 9); // Small icon
            }
          }
        }
      }
    }
  }

  @Shadow
  private PlayerEntity getCameraPlayer() {
    return null;
  }

  @Shadow
  private LivingEntity getRiddenEntity() {
    return null;
  }

  @Shadow
  private int getHeartCount(LivingEntity entity) {
    return 0;
  }

}