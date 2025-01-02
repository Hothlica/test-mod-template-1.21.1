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
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Item CERULEAN_DUST = add("cerulean_dust", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(-1).saturationModifier(0.2f).build())), ItemGroups.FOOD_AND_DRINK);

    public static void init(){
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }

    private static <T extends Item> T add(String name, T item, @Nullable RegistryKey<ItemGroup> itemGroup) {
        if (itemGroup != null) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
        }
        ITEMS.put(item, Identifier.of(MOD_ID, name));
        return item;
    }

    public static final RegistryKey<ItemGroup> TESTMOD_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(TestMod.MOD_ID, "item_group"));
    public static final ItemGroup TESTMOD_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TestItems.CERULEAN_DUST))
            .displayName(Text.translatable("itemGroup.testmod.items"))
            .build();
}
