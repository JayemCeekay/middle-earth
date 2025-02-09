package net.jukoz.me.item.items;

import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StarlightPhialItem extends Item {
    public StarlightPhialItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.getRegistryKey().getValue().equals(ModDimensions.WORLD_KEY.getValue())) {
            if (!user.isCreative()) {
                //user.getInventory().removeStack(user.getActiveHand().ordinal());
                user.getStackInHand(hand).decrement(1);
            }
            ModDimensions.teleportPlayerToME(user);
        }
        return super.use(world, user, hand);
    }
}
