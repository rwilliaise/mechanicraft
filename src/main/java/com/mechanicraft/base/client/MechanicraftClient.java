package com.mechanicraft.base.client;

import com.mechanicraft.base.MechanicraftRegistry;
import com.mechanicraft.base.client.block.PlateBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class MechanicraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.INSTANCE.register(MechanicraftRegistry.PLATE_BLOCKENTITY, PlateBlockEntityRenderer::new);
	}
}
