package net.cakewalk.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class GoldenLeadLinedArmorMaterial implements ArmorMaterial {
  private static final int[] BASE_DURABILITY = new int[] { 14, 15, 16, 12 };
  private static final int[] PROTECTION_AMOUNTS = new int[] { 2, 4, 5, 2 };

  @Override
  public int getDurability(EquipmentSlot equipmentSlot) {
    return BASE_DURABILITY[equipmentSlot.getEntitySlotId()] * 20;
  }

  @Override
  public int getProtectionAmount(EquipmentSlot equipmentSlot) {
    return PROTECTION_AMOUNTS[equipmentSlot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return 25;
  }

  @Override
  public SoundEvent getEquipSound() {
    return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return Ingredient.ofItems(Items.GOLD_INGOT);
  }

  @Environment(EnvType.CLIENT)
  public String getName() {
    return "golden_lead_armor";
  }

  @Override
  public float getToughness() {
    return 0.0F;
  }

  @Override
  public float getKnockbackResistance() {
    return 0.0F;
  }

}