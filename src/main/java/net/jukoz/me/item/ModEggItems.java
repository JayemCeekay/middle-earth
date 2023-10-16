package net.jukoz.me.item;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.datageneration.content.models.SimpleSpawnEggItemModel;
import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.item.utils.ModItemGroups;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEggItems {

    public static final Item BARROW_WIGHT_SPAWN_EGG = registerItem("barrow_wight_spawn_egg",
            new SpawnEggItem(ModEntities.BARROW_WIGHT, 1852734, 2456136, new Item.Settings()));
    public static final Item CAVE_TROLL_SPAWN_EGG = registerItem("cave_troll_spawn_egg",
            new SpawnEggItem(ModEntities.CAVE_TROLL, 4671043, 3485226, new Item.Settings()));
    public static final Item DURIN_FOLK_SPAWN_EGG = registerItem("durin_folk_spawn_egg",
            new SpawnEggItem(ModEntities.DURIN_FOLK, 4541263, 8090735, new Item.Settings()));
    public static final Item HOBBIT_SPAWN_EGG = registerItem("hobbit_spawn_egg",
            new SpawnEggItem(ModEntities.HOBBIT, 11131454, 13810794, new Item.Settings()));
    public static final Item GALADHRIM_ELF_SPAWN_EGG = registerItem("galadhrim_elf_spawn_egg",
            new SpawnEggItem(ModEntities.GALADHRIM_ELF, 15388227, 8950161, new Item.Settings()));
    public static final Item GONDORIAN_SPAWN_EGG = registerItem("gondorian_spawn_egg",
            new SpawnEggItem(ModEntities.GONDORIAN, 16250871, 2500134, new Item.Settings()));
    public static final Item MORDOR_ORC_SPAWN_EGG = registerItem("mordor_orc_spawn_egg",
            new SpawnEggItem(ModEntities.MORDOR_ORC, 4209210, 7682104, new Item.Settings()));
    public static final Item SNOW_TROLL_SPAWN_EGG = registerItem("snow_troll_spawn_egg",
            new SpawnEggItem(ModEntities.SNOW_TROLL, 12770027, 9739424, new Item.Settings()));
    public static final Item MIRKWOOD_SPIDER_SPAWN_EGG = registerItem("mirkwood_spider_spawn_egg",
            new SpawnEggItem(ModEntities.MIRKWOOD_SPIDER, 657930, 11669520, new Item.Settings()));

    // Animals
    public static final Item CRAB_SPAWN_EGG = registerItem("crab_spawn_egg",
            new SpawnEggItem(ModEntities.CRAB, 15350073,15764576, new Item.Settings()));



    private static Item registerItem(String name, Item item) {
        ModItemGroups.SPAWN_EGGS_CONTENTS.add(item.getDefaultStack());
        SimpleSpawnEggItemModel.items.add(item);
        return Registry.register(Registries.ITEM, new Identifier(MiddleEarth.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MiddleEarth.LOGGER.debug("Registering Mod Egg Items for " + MiddleEarth.MOD_ID);
    }
}
