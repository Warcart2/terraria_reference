
package net.warcar.terrariareference.entity.renderer;

import net.warcar.terrariareference.procedures.WaspEntityShakingConditionProcedure;
import net.warcar.terrariareference.entity.WaspEntity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import java.util.Collections;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class WaspRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(WaspEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelwasp(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("terraria_reference:textures/entities/wasp.png");
					}

					@Override
					protected boolean func_230495_a_(LivingEntity _ent) {
						Entity entity = _ent;
						World world = entity.world;
						double x = entity.getPosX();
						double y = entity.getPosY();
						double z = entity.getPosZ();
						return WaspEntityShakingConditionProcedure.executeProcedure(Collections.emptyMap());
					}
				};
			});
		}
	}

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
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
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

}
