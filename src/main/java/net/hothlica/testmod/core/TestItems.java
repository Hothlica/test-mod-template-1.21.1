package net.hothlica.testmod.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.hothlica.testmod.core.TestMod.MOD_ID;

public class TestItems {

    //Custom item group stuff
    public static final RegistryKey<ItemGroup> TESTMOD_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(TestMod.MOD_ID, "item_group"));
    public static final ItemGroup TESTMOD_ITEM_GROUP = FabricItemGroup.builder().icon(() -> new ItemStack(TestItems.CERULEAN_DUST))
            .displayName(Text.translatable("itemGroup.testmod.items"))
            .build();

    //Map of the custom items
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //Initialize custom items
    public static final Item CERULEAN_DUST = add("cerulean_dust", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(-1).saturationModifier(0.2f).build())), TESTMOD_ITEM_GROUP_KEY);

    public static void init(){
        //Register custom item group (tab)
        TestMod.LOGGER.info("Registering item groups for " + TestMod.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, TESTMOD_ITEM_GROUP_KEY.getValue(), TESTMOD_ITEM_GROUP);
        //Register items
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }

    //Put items into the ITEM map
    private static <T extends Item> T add(String name, T item, @Nullable RegistryKey<ItemGroup> itemGroup) {
        if (itemGroup != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
        }
        ITEMS.put(item, Identifier.of(MOD_ID, name));
        return item;
    }

}
