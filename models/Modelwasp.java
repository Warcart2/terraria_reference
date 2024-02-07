// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelwasp extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer stinger;
	private final ModelRenderer rightwing_bone;
	private final ModelRenderer leftwing_bone;
	private final ModelRenderer leg_front;
	private final ModelRenderer leg_front_r1;
	private final ModelRenderer leg_mid;
	private final ModelRenderer leg_mid_r1;
	private final ModelRenderer leg_back;
	private final ModelRenderer leg_back_r1;

	public Modelwasp() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, 19.0F, 0.0F);
		body.setTextureOffset(-2, -2).addBox(-3.5F, -4.0F, -6.0F, 7.0F, 7.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(39, 0).addBox(-2.5F, -4.0F, -10.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(39, 0).addBox(1.5F, -4.0F, -10.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		stinger = new ModelRenderer(this);
		stinger.setRotationPoint(0.0F, -1.0F, 1.0F);
		body.addChild(stinger);
		stinger.setTextureOffset(25, 6).addBox(0.0F, 0.0F, 5.0F, 0.0F, 1.0F, 3.0F, 0.0F, false);

		rightwing_bone = new ModelRenderer(this);
		rightwing_bone.setRotationPoint(-1.5F, -4.0F, -3.0F);
		body.addChild(rightwing_bone);
		setRotationAngle(rightwing_bone, 0.2618F, -0.2618F, 0.0F);
		rightwing_bone.setTextureOffset(-1, 17).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 0.0F, 7.0F, 0.0F, false);

		leftwing_bone = new ModelRenderer(this);
		leftwing_bone.setRotationPoint(1.5F, -4.0F, -3.0F);
		body.addChild(leftwing_bone);
		setRotationAngle(leftwing_bone, 0.2618F, 0.2618F, 0.0F);
		leftwing_bone.setTextureOffset(8, 23).addBox(0.0F, 0.0F, 0.0F, 10.0F, 0.0F, 7.0F, 0.0F, false);

		leg_front = new ModelRenderer(this);
		leg_front.setRotationPoint(1.5F, 3.0F, -2.0F);
		body.addChild(leg_front);

		leg_front_r1 = new ModelRenderer(this);
		leg_front_r1.setRotationPoint(-2.0F, 2.0F, 2.0F);
		leg_front.addChild(leg_front_r1);
		setRotationAngle(leg_front_r1, 0.3927F, 0.0F, 0.0F);
		leg_front_r1.setTextureOffset(26, 1).addBox(-3.0F, -3.0F, -2.0F, 7.0F, 2.0F, 0.0F, 0.0F, false);

		leg_mid = new ModelRenderer(this);
		leg_mid.setRotationPoint(1.5F, 3.0F, 0.0F);
		body.addChild(leg_mid);

		leg_mid_r1 = new ModelRenderer(this);
		leg_mid_r1.setRotationPoint(-2.0F, 2.0F, 0.0F);
		leg_mid.addChild(leg_mid_r1);
		setRotationAngle(leg_mid_r1, 0.4363F, 0.0F, 0.0F);
		leg_mid_r1.setTextureOffset(26, 3).addBox(-3.0F, -2.0F, 0.0F, 7.0F, 2.0F, 0.0F, 0.0F, false);

		leg_back = new ModelRenderer(this);
		leg_back.setRotationPoint(1.5F, 3.0F, 2.0F);
		body.addChild(leg_back);

		leg_back_r1 = new ModelRenderer(this);
		leg_back_r1.setRotationPoint(-2.0F, 2.0F, -2.0F);
		leg_back.addChild(leg_back_r1);
		setRotationAngle(leg_back_r1, 0.3927F, 0.0F, 0.0F);
		leg_back_r1.setTextureOffset(26, 5).addBox(-3.0F, -1.5F, 2.0F, 7.0F, 2.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		this.rightwing_bone.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leg_mid.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_back.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leftwing_bone.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg_front_r1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_mid_r1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg_back_r1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_front.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
	}
}