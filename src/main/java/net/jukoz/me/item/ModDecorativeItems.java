package net.jukoz.me.item;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.ModDecorativeBlocks;
import net.jukoz.me.item.utils.ModItemGroups;
import net.jukoz.me.item.utils.ModVerticallyAttachableBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModDecorativeItems {
    public static final Item DWARVEN_LANTERN = registerItem("dwarven_lantern",
            new ModVerticallyAttachableBlockItem(ModDecorativeBlocks.DWARVEN_LANTERN, ModDecorativeBlocks.WALL_DWARVEN_LANTERN, new Item.Settings(), new Direction[]{Direction.DOWN, Direction.UP}));
    public static final Item SILVER_LANTERN = registerItem("silver_lantern",
            new ModVerticallyAttachableBlockItem(ModDecorativeBlocks.SILVER_LANTERN, ModDecorativeBlocks.WALL_SILVER_LANTERN, new Item.Settings(), new Direction[]{Direction.DOWN, Direction.UP}));
    public static final Item ALLOY_FURNACE = registerItem("alloy_furnace",
            new BlockItem(ModDecorativeBlocks.ALLOY_FURNACE, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        ModItemGroups.DECORATIVES_BLOCKS_CONTENT.add(item.getDefaultStack());
        return Registry.register(Registries.ITEM, new Identifier(MiddleEarth.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MiddleEarth.LOGGER.debug("Registering Mod Decorative Items for " + MiddleEarth.MOD_ID);
    }
}
