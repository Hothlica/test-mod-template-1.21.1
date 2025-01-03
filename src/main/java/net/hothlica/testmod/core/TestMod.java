package net.hothlica.testmod.core;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//Mod items/blocks tab list is initialized/registered in TestItems
		//TestEntities.init();
		TestBlocks.init();
		TestItems.init();
		//TestComponentTypes.init();
		//TestRecipeSerializers.init();
		//TestSoundEvents.initialize();

	}
}