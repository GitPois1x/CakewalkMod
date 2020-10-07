package net.cakewalk.entity.model;

import com.google.common.collect.ImmutableList;

import net.cakewalk.entity.DireWolfEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.util.math.MathHelper;

import net.minecraft.client.render.entity.model.WolfEntityModel;

@Environment(EnvType.CLIENT)
public class DireWolfModel<T extends DireWolfEntity> extends CompositeEntityModel<T> {
  private final ModelPart head;
  private final ModelPart torso;
  private final ModelPart neckFur;
  private final ModelPart backFurL;
  private final ModelPart backFurR;
  private final ModelPart tail;
  private final ModelPart tailTip;
  private final ModelPart legBackL;
  private final ModelPart legBackR;
  private final ModelPart frontLegL;
  private final ModelPart frontLegR;
  private final ModelPart earL;
  private final ModelPart earR;

  public DireWolfModel() {
    torso = (new ModelPart(this)).setTextureSize(64, 64);
    torso.setPivot(0.0F, 13.0F, -7.0F);
    torso.setTextureOffset(24, 44).addCuboid(-4.0F, -4.0F, 2.0F, 8.0F, 8.0F, 12.0F, 0.0F, false);

    neckFur = (new ModelPart(this)).setTextureSize(64, 64);
    neckFur.setPivot(0.0F, -1.0F, 3.0F);
    torso.addChild(neckFur);

    neckFur.setTextureOffset(32, 27).addCuboid(-5.0F, -5.0F, -5.0F, 10.0F, 11.0F, 6.0F, 0.0F, false);

    backFurL = (new ModelPart(this)).setTextureSize(64, 64);
    backFurL.setPivot(-4.0F, -1.0F, 10.0F);
    torso.addChild(backFurL);

    backFurL.setTextureOffset(53, 44).addCuboid(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

    backFurR = (new ModelPart(this)).setTextureSize(64, 64);
    backFurR.setPivot(4.0F, -1.0F, 10.0F);
    torso.addChild(backFurR);

    backFurR.setTextureOffset(53, 44).addCuboid(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

    tail = (new ModelPart(this)).setTextureSize(64, 64);
    tail.setPivot(0.0F, -2.0F, 14.0F);
    torso.addChild(tail);

    tail.setTextureOffset(56, 23).addCuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    tail.setTextureOffset(36, 17).addCuboid(-2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);

    tailTip = (new ModelPart(this)).setTextureSize(64, 64);
    tailTip.setPivot(0.0F, 0.0F, 7.0F);
    tail.addChild(tailTip);

    tailTip.setTextureOffset(50, 16).addCuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

    legBackL = (new ModelPart(this)).setTextureSize(64, 64);
    legBackL.setPivot(-3.0F, 4.0F, 13.0F);
    torso.addChild(legBackL);
    legBackL.setTextureOffset(28, 45).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    legBackL.setTextureOffset(28, 50).addCuboid(-1.0F, 3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    legBackL.setTextureOffset(55, 46).addCuboid(-1.0F, 6.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

    legBackR = (new ModelPart(this)).setTextureSize(64, 64);
    legBackR.setPivot(3.0F, 4.0F, 13.0F);
    torso.addChild(legBackR);
    legBackR.setTextureOffset(28, 45).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    legBackR.setTextureOffset(28, 50).addCuboid(-1.0F, 3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    legBackR.setTextureOffset(55, 46).addCuboid(-1.0F, 6.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

    frontLegL = (new ModelPart(this)).setTextureSize(64, 64);
    frontLegL.setPivot(-3.0F, 4.0F, 2.0F);
    torso.addChild(frontLegL);
    frontLegL.setTextureOffset(28, 24).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
    frontLegL.setTextureOffset(55, 46).addCuboid(-1.0F, 6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

    frontLegR = (new ModelPart(this)).setTextureSize(64, 64);
    frontLegR.setPivot(3.0F, 4.0F, 2.0F);
    torso.addChild(frontLegR);
    frontLegR.setTextureOffset(28, 24).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
    frontLegR.setTextureOffset(55, 46).addCuboid(-1.0F, 6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

    head = (new ModelPart(this)).setTextureSize(64, 64);
    head.setPivot(0.0F, 11.0F, -8.0F);

    head.setTextureOffset(34, 0).addCuboid(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
    head.setTextureOffset(26, 15).addCuboid(-2.0F, 0.0F, -10.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

    earL = (new ModelPart(this)).setTextureSize(64, 64);
    earL.setPivot(-2.0F, -4.0F, -2.0F);
    head.addChild(earL);

    earL.setTextureOffset(31, 2).addCuboid(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
    earL.setTextureOffset(35, 0).addCuboid(-1.0F, -4.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

    earR = (new ModelPart(this)).setTextureSize(64, 64);
    earR.setPivot(2.0F, -4.0F, -2.0F);
    head.addChild(earR);

    earR.setTextureOffset(31, 2).addCuboid(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
    earR.setTextureOffset(35, 0).addCuboid(-1.0F, -4.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
  }

  @Override
  public Iterable<ModelPart> getParts() {
    return ImmutableList.of(this.head, this.torso);
  }

  @Override
  public void setAngles(T stoneGolem, float f, float g, float h, float i, float j) {
    this.neckFur.pitch = -0.2618F;
    this.backFurL.yaw = -0.3491F;
    this.backFurR.yaw = 0.3491F;
    this.tail.pitch = 0.2182F;
    this.earL.yaw = 0.4363F;
    this.earR.yaw = -0.4363F;
    this.tailTip.pitch = 0.3927F;
    // setRotationAngle(neckFur, -0.2618F, 0.0F, 0.0F);
    // setRotationAngle(backFurL, 0.0F, -0.3491F, 0.0F);
    // setRotationAngle(backFurR, 0.0F, 0.3491F, 0.0F);
    // setRotationAngle(tail, 0.2182F, 0.0F, 0.0F);
    // setRotationAngle(head, 0.0F, 0.0F, 0.0F);
    // setRotationAngle(earL, 0.0F, 0.4363F, 0.0F);
    // setRotationAngle(earR, 0.0F, -0.4363F, 0.0F);
    // setRotationAngle(tailTip, 0.3927F, 0.0F, 0.0F);
    this.head.yaw = i * 0.0077453292F;
    this.head.pitch = j * 0.0017453292F;
    this.legBackR.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
    this.legBackL.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
    this.frontLegR.pitch = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
    this.frontLegL.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
    // this.rightLeg.yaw = 0.0F;
    // this.leftLeg.yaw = 0.0F;
    // this.rightLeg.pitch = -0.3F * MathHelper.method_24504(f, 13.0F) * g +
    // 0.1745F;
    // this.leftLeg.pitch = 0.3F * MathHelper.method_24504(f, 13.0F) * g + 0.1745F;
    // this.torso.pitch = 0.7854F;
    // this.rightArm.pitch = 0.5F * MathHelper.method_24504(f, 13.0F) * g - 0.5236F;
    // this.leftArm.pitch = -0.5F * MathHelper.method_24504(f, 13.0F) * g - 0.5236F;

  }

}
