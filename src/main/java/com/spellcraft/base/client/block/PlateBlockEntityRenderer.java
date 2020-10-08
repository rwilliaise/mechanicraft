package com.spellcraft.base.client.block;

import com.spellcraft.base.block.entity.PlateBlockEntity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class PlateBlockEntityRenderer extends BlockEntityRenderer<PlateBlockEntity> {

	public PlateBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(PlateBlockEntity entity, float tickDelta, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light, int overlay) {
		double offset = entity.getPos().getSquaredDistance(new Vec3i(0, 0, 0));
		matrices.push();
		matrices.translate(0.5, 0, 0.5);
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
		for (int i = 0; i < entity.size(); i++) {
			ItemStack stack = entity.getStack(i);
			if (stack.isEmpty()) continue;
			for (int j = 0; j < stack.getCount(); j++) {
				matrices.translate(0, 0, -0.03125);
				matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float)Math.sin((i + 1) * (j + 1) + offset) * 90f
																		 + (float)Math.sin((i + 1) * (j + 1) * 0.25
																		 				 + offset * 0.25) * 45f));
				matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)Math.sin((entity.getWorld().getTime() + tickDelta + offset) * 0.1) * 0.1f));
				int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
				MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
			}
		}
		matrices.pop();
	}
	
}
