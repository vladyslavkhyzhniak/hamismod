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
import net.minecraft.util.Mth;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisModel extends EntityModel<HamisEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "hamis"), "main");

	private final ModelPart boby;
	private final ModelPart legM;
	private final ModelPart legR;
	private final ModelPart legL;

	public HamisModel(ModelPart root) {
		this.boby = root.getChild("boby");
		this.legM = root.getChild("legM");
		this.legR = root.getChild("legR");
		this.legL = root.getChild("legL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition boby = partdefinition.addOrReplaceChild("boby", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(2, 10).addBox(0.0F, -6.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(7, 9).addBox(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 20.0F, 0.0F));

		PartDefinition legM = partdefinition.addOrReplaceChild("legM", CubeListBuilder.create().texOffs(4, 6).addBox(-4.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 23.0F, -1.0F));

		legM.addOrReplaceChild("part01", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 0.0F));
		legM.addOrReplaceChild("part02", CubeListBuilder.create().texOffs(12, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 0.0F));

		PartDefinition legR = partdefinition.addOrReplaceChild("legR", CubeListBuilder.create(), PartPose.offset(3.0F, 22.0F, 2.0F));
		legR.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(12, 10).addBox(-1.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));
		legR.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(8, 6).addBox(-1.0F, 1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -1.0F));
		legR.addOrReplaceChild("part8", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));


		PartDefinition legL = partdefinition.addOrReplaceChild("legL", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));
		legL.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(12, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, 0.0F));
		legL.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -3.0F, 0.0F));
		legL.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 1.0F));
		legL.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 1.0F));
		legL.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 1.0F));
		legL.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 1.0F));
		legL.addOrReplaceChild("part7", CubeListBuilder.create().texOffs(12, 8).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(HamisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.legR.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.legM.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		boby.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legM.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legR.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		legL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}