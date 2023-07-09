package net.jesteur.me.persistent;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class PersistentStateManager extends PersistentState {
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return nbt;
    }



}
