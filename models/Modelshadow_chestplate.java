// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelshadow_chestplate extends EntityModel<Entity> {
	private final ModelRenderer Body;
	private final ModelRenderer RightArm2;
	private final ModelRenderer LeftArm2;

	public Modelshadow_chestplate() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
		Body.setTextureOffset(32, 39).addBox(-8.5F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.25F, false);
		Body.setTextureOffset(32, 33).addBox(4.5F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.25F, false);

		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(-5.0F, 2.0F, 0.0F);
		RightArm2.setTextureOffset(16, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		LeftArm2 = new ModelRenderer(this);
		LeftArm2.setRotationPoint(5.0F, 2.0F, 0.0F);
		LeftArm2.setTextureOffset(32, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		this.RightArm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LeftArm2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}