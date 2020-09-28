package net.cakewalk.item;

import java.util.function.Supplier;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum ExtraToolMaterial implements ToolMaterial {
  LEAD(0, 200, 1.2F, 2.0F, 24, () -> {
    return Ingredient.ofItems(Items.IRON_INGOT);
  });

  private final int miningLevel;
  private final int itemDurability;
  private final float miningSpeedMultiplier;
  private final float attackDamage;
  private final int enchantability;
  private final Lazy<Ingredient> repairIngredient;

  ExtraToolMaterial(int miningLevel, int itemDurability, float miningSpeedMultiplier, float attackDamage,
      int enchantability, Supplier<Ingredient> repairIngredient) {
    this.miningLevel = miningLevel;
    this.itemDurability = itemDurability;
    this.miningSpeedMultiplier = miningSpeedMultiplier;
    this.attackDamage = attackDamage;
    this.enchantability = enchantability;
    this.repairIngredient = new Lazy<>(repairIngredient);
  }

  @Override
  public int getDurability() {
    return this.itemDurability;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return this.miningSpeedMultiplier;
  }

  @Override
  public float getAttackDamage() {
    return this.attackDamage;
  }

  @Override
  public int getMiningLevel() {
    return this.miningLevel;
  }

  @Override
  public int getEnchantability() {
    return this.enchantability;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
  }
}