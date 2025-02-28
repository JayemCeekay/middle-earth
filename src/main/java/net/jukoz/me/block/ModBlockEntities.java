package net.jukoz.me.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.alloyfurnace.AlloyFurnaceEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<AlloyFurnaceEntity> ALLOY_FURNACE;

    public static void registerBlockEntities() {
        ALLOY_FURNACE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MiddleEarth.MOD_ID, "alloy_furnace"),
                FabricBlockEntityTypeBuilder.create(AlloyFurnaceEntity::new,
                        ModDecorativeBlocks.ALLOY_FURNACE).build(null));
    }
}
