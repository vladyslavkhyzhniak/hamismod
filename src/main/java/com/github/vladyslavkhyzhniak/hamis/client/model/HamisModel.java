package com.github.vladyslavkhyzhniak.hamis.client.model;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class HamisModel<T extends HamisEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hamis"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart legM;
	private final ModelPart leg01;
	private final ModelPart leg02;
	private final ModelPart legL;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart leg7;
	private final ModelPart legR;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart leg8;

	public HamisModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.legM = this.root.getChild("legM");
		this.leg01 = this.legM.getChild("leg01");
		this.leg02 = this.legM.getChild("leg02");
		this.legL = this.root.getChild("legL");
		this.leg1 = this.legL.getChild("leg1");
		this.leg2 = this.legL.getChild("leg2");
		this.leg3 = this.legL.getChild("leg3");
		this.leg4 = this.legL.getChild("leg4");
		this.leg5 = this.legL.getChild("leg5");
		this.leg6 = this.legL.getChild("leg6");
		this.leg7 = this.legL.getChild("leg7");
		this.legR = this.root.getChild("legR");
		this.bone = this.legR.getChild("bone");
		this.bone2 = this.legR.getChild("bone2");
		this.leg8 = this.legR.getChild("leg8");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-2.0F, 17.0F, -1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(2, 10).addBox(0.0F, -6.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(7, 9).addBox(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 1.0F));

		PartDefinition legM = root.addOrReplaceChild("legM", CubeListBuilder.create().texOffs(4, 6).addBox(0.0F, 2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 0.0F));

		PartDefinition leg01 = legM.addOrReplaceChild("leg01", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 0.0F));

		PartDefinition leg02 = legM.addOrReplaceChild("leg02", CubeListBuilder.create().texOffs(12, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 0.0F));

		PartDefinition legL = root.addOrReplaceChild("legL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg1 = legL.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(12, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition leg2 = legL.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 1.0F));

		PartDefinition leg3 = legL.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 2.0F));

		PartDefinition leg4 = legL.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 2.0F));

		PartDefinition leg5 = legL.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, 2.0F));

		PartDefinition leg6 = legL.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 6.0F, 2.0F));

		PartDefinition leg7 = legL.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(12, 8).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 2.0F));

		PartDefinition legR = root.addOrReplaceChild("legR", CubeListBuilder.create(), PartPose.offset(4.0F, 1.0F, 1.0F));

		PartDefinition bone = legR.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(12, 10).addBox(-1.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 1.0F));

		PartDefinition bone2 = legR.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(8, 6).addBox(-1.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 1.0F));

		PartDefinition leg8 = legR.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(HamisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}