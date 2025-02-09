package net.jukoz.me.item.utils;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModRessourceItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    CLOAK("cloak", 7, new int[] { 1, 3, 0, 0}, 0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, 0.0F,
            () -> Ingredient.ofItems(Items.LEATHER)),
    TUNIC_CLOAK("tunic_cloak", 10, new int[] { 0, 4, 0, 0}, 0,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, 0.0F,
            () -> Ingredient.ofItems(Items.LEATHER)),
    FUR_CLOAK("fur_cloak", 7, new int[] { 1, 3, 0, 0},
            0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, 0.0F,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    CHAINMAIL_FUR_CLOAK("chainmail_fur_cloak", 15, new int[] { 0, 5, 0, 0},
            0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0, 0.0F,
            () -> Ingredient.ofItems(Items.IRON_INGOT)),
    NAZGUL_CLOAK("nazgul_cloak", 15, new int[] { 2, 5, 4, 2},
            0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0, 0.0F,
            () -> Ingredient.ofItems(ModRessourceItems.MORGUL_INGOT)),
    MORDOR_ORC_ARMOR("mordor_orc_armor", 16, new int[] { 2, 6, 5, 2},
            0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0, 0.0F,
            () -> Ingredient.ofItems(ModRessourceItems.ORC_STEEL)),
    ;

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final int toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts,
                      int enchantability, SoundEvent equipSound, int toughness,
                      float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return MiddleEarth.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
