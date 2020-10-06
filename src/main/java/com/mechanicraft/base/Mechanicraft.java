package com.mechanicraft.base;

import net.fabricmc.api.ModInitializer;

public class Mechanicraft implements ModInitializer {

	public static final String MOD_ID = "mechanicraft";

	@Override
	public void onInitialize() {
		MechanicraftRegistry.initialize();
	}
}
