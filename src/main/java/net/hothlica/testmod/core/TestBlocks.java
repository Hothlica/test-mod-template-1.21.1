package net.hothlica.testmod.core;

import net.hothlica.testmod.common.block.*;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.hothlica.testmod.core.TestItems.TESTMOD_ITEM_GROUP_KEY;
import static net.hothlica.testmod.core.TestMod.MOD_ID;

public class TestBlocks {

    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //Register the blocks
    public static final Block CERULEAN_BRICKS = add("cerulean_bricks", new CeruleanBricks(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)), ItemGroups.BUILDING_BLOCKS);

    //Batch Registration
    private static <T extends Block> T add(String name, T block, @Nullable RegistryKey<ItemGroup> itemGroup) {
        BLOCKS.put(block, Identifier.of(MOD_ID, name));
        if (itemGroup != null) {
            var blockItem = new BlockItem(block, new Item.Settings());
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(blockItem));
            ITEMS.put(blockItem, BLOCKS.get(block));
        }
        return block;
    }
    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
    }
}
