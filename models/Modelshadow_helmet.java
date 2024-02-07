// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public static class Modelshadow_helmet extends EntityModel<Entity> {
	private final ModelRenderer Head;
	private final ModelRenderer HatLayer_r1;

	public Modelshadow_helmet() {
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		HatLayer_r1 = new ModelRenderer(this);
		HatLayer_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		Head.addChild(HatLayer_r1);
		setRotationAngle(HatLayer_r1, 0.3927F, 0.0F, 0.0F);
		HatLayer_r1.setTextureOffset(0, 0).addBox(1.5F, -27.0F, 15.0F, 1.0F, 1.0F, 3.0F, 0.5F, false);
		HatLayer_r1.setTextureOffset(0, 0).addBox(1.0F, -24.25F, 13.75F, 1.0F, 1.0F, 3.0F, 0.5F, false);
		HatLayer_r1.setTextureOffset(0, 0).addBox(-2.0F, -24.25F, 13.75F, 1.0F, 1.0F, 3.0F, 0.5F, false);
		HatLayer_r1.setTextureOffset(0, 0).addBox(-2.5F, -27.0F, 15.0F, 1.0F, 1.0F, 3.0F, 0.5F, false);
		HatLayer_r1.setTextureOffset(0, 16).addBox(-3.0F, -30.0F, 11.0F, 1.0F, 1.0F, 8.0F, 0.5F, false);
		HatLayer_r1.setTextureOffset(0, 16).addBox(2.0F, -30.0F, 11.0F, 1.0F, 1.0F, 8.0F, 0.5F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}