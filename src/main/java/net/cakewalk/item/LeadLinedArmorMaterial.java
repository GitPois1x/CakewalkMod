package net.cakewalk.item;

import net.cakewalk.init.ItemInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class LeadLinedArmorMaterial implements ArmorMaterial {
  private static final int[] BASE_DURABILITY = new int[] { 14, 15, 16, 12 };
  private static final int[] PROTECTION_AMOUNTS = new int[] { 3, 6, 8, 3 };

  @Override
  public int getDurability(EquipmentSlot equipmentSlot) {
    return BASE_DURABILITY[equipmentSlot.getEntitySlotId()] * 40;
  }

  @Override
  public int getProtectionAmount(EquipmentSlot equipmentSlot) {
    return PROTECTION_AMOUNTS[equipmentSlot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return 20;
  }

  @Override
  public SoundEvent getEquipSound() {
    return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return Ingredient.ofItems(ItemInit.LEAD_INGOT_ITEM);
  }

  @Environment(EnvType.CLIENT)
  public String getName() {
    return "lead_lined_armor";
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