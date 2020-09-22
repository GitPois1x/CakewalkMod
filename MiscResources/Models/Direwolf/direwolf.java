// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class direwolf extends EntityModel<Entity> {
	private final ModelRenderer Torso;
	private final ModelRenderer NeckFur;
	private final ModelRenderer BackfurL;
	private final ModelRenderer BackfurR;
	private final ModelRenderer Tail;
	private final ModelRenderer TailTip;
	private final ModelRenderer LegBackL;
	private final ModelRenderer LegBackR;
	private final ModelRenderer FrontLegL;
	private final ModelRenderer FrontLegR;
	private final ModelRenderer Head;
	private final ModelRenderer EarL;
	private final ModelRenderer EarR;

	public direwolf() {
		textureWidth = 64;
		textureHeight = 64;

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 13.0F, -7.0F);
		Torso.setTextureOffset(24, 44).addBox(-4.0F, -4.0F, 2.0F, 8.0F, 8.0F, 12.0F, 0.0F, false);

		NeckFur = new ModelRenderer(this);
		NeckFur.setRotationPoint(0.0F, -1.0F, 3.0F);
		Torso.addChild(NeckFur);
		setRotationAngle(NeckFur, -0.2618F, 0.0F, 0.0F);
		NeckFur.setTextureOffset(32, 27).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 11.0F, 6.0F, 0.0F, false);

		BackfurL = new ModelRenderer(this);
		BackfurL.setRotationPoint(-4.0F, -1.0F, 10.0F);
		Torso.addChild(BackfurL);
		setRotationAngle(BackfurL, 0.0F, -0.3491F, 0.0F);
		BackfurL.setTextureOffset(53, 44).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackfurR = new ModelRenderer(this);
		BackfurR.setRotationPoint(4.0F, -1.0F, 10.0F);
		Torso.addChild(BackfurR);
		setRotationAngle(BackfurR, 0.0F, 0.3491F, 0.0F);
		BackfurR.setTextureOffset(53, 44).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, -2.0F, 14.0F);
		Torso.addChild(Tail);
		setRotationAngle(Tail, 0.2182F, 0.0F, 0.0F);
		Tail.setTextureOffset(56, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Tail.setTextureOffset(36, 17).addBox(-2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);

		TailTip = new ModelRenderer(this);
		TailTip.setRotationPoint(0.0F, 0.0F, 7.0F);
		Tail.addChild(TailTip);
		setRotationAngle(TailTip, 0.3927F, 0.0F, 0.0F);
		TailTip.setTextureOffset(50, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		LegBackL = new ModelRenderer(this);
		LegBackL.setRotationPoint(-3.0F, 4.0F, 13.0F);
		Torso.addChild(LegBackL);
		LegBackL.setTextureOffset(28, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		LegBackL.setTextureOffset(28, 50).addBox(-1.0F, 3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		LegBackL.setTextureOffset(55, 46).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		LegBackR = new ModelRenderer(this);
		LegBackR.setRotationPoint(3.0F, 4.0F, 13.0F);
		Torso.addChild(LegBackR);
		LegBackR.setTextureOffset(28, 45).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		LegBackR.setTextureOffset(28, 50).addBox(-1.0F, 3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		LegBackR.setTextureOffset(55, 46).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		FrontLegL = new ModelRenderer(this);
		FrontLegL.setRotationPoint(-3.0F, 4.0F, 2.0F);
		Torso.addChild(FrontLegL);
		FrontLegL.setTextureOffset(28, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		FrontLegL.setTextureOffset(55, 46).addBox(-1.0F, 6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		FrontLegR = new ModelRenderer(this);
		FrontLegR.setRotationPoint(3.0F, 4.0F, 2.0F);
		Torso.addChild(FrontLegR);
		FrontLegR.setTextureOffset(28, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		FrontLegR.setTextureOffset(55, 46).addBox(-1.0F, 6.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 11.0F, -8.0F);
		setRotationAngle(Head, 0.0F, 0.0F, 0.0F);
		Head.setTextureOffset(34, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
		Head.setTextureOffset(26, 15).addBox(-2.0F, 0.0F, -10.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		EarL = new ModelRenderer(this);
		EarL.setRotationPoint(-2.0F, -4.0F, -2.0F);
		Head.addChild(EarL);
		setRotationAngle(EarL, 0.0F, 0.4363F, 0.0F);
		EarL.setTextureOffset(31, 2).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		EarL.setTextureOffset(35, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		EarR = new ModelRenderer(this);
		EarR.setRotationPoint(2.0F, -4.0F, -2.0F);
		Head.addChild(EarR);
		setRotationAngle(EarR, 0.0F, -0.4363F, 0.0F);
		EarR.setTextureOffset(31, 2).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		EarR.setTextureOffset(35, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Torso.render(matrixStack, buffer, packedLight, packedOverlay);
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}