package com.spellcraft.base.client;

import com.spellcraft.base.SpellcraftRegistry;
import com.spellcraft.base.client.block.PlateBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class SpellcraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.INSTANCE.register(SpellcraftRegistry.PLATE_BLOCKENTITY, PlateBlockEntityRenderer::new);
	}
}
