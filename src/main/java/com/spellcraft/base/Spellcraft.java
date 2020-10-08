package com.spellcraft.base;

import net.fabricmc.api.ModInitializer;

public class Spellcraft implements ModInitializer {

	public static final String MOD_ID = "mechanicraft";

	@Override
	public void onInitialize() {
		SpellcraftRegistry.initialize();
	}
}
