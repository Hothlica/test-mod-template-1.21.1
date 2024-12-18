package net.hothlica.testmod.core;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//TestEntities.init();
		TestBlocks.init();
		TestItems.init();
		//TestComponentTypes.init();
		//TestRecipeSerializers.init();
		//TestSoundEvents.initialize();

	}
}