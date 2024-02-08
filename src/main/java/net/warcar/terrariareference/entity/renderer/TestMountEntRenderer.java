
package net.warcar.terrariareference.entity.renderer;

import net.warcar.terrariareference.entity.TestMountEntEntity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class TestMountEntRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(TestMountEntEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modeleye_of_cthulhu(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("terraria_reference:textures/entities/eye_of_cthulhu.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.9.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modeleye_of_cthulhu extends EntityModel<Entity> {
		private final ModelRenderer bb_main;
		private final ModelRenderer cube_r1;

		public Modeleye_of_cthulhu() {
			textureWidth = 256;
			textureHeight = 256;
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.setTextureOffset(0, 0).addBox(-32.0F, -40.0F, -32.0F, 64.0F, 64.0F, 64.0F, 0.0F, false);
			bb_main.setTextureOffset(21, 85).addBox(0.0F, -40.0F, 32.0F, 0.0F, 64.0F, 43.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, -8.0F, 53.5F);
			bb_main.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 0.0F, 1.5708F);
			cube_r1.setTextureOffset(21, 85).addBox(0.0F, -32.0F, -21.5F, 0.0F, 64.0F, 43.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.bb_main.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.bb_main.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}

}
