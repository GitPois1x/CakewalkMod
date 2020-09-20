package net.cakewalk.init;

import net.cakewalk.item.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
        // Items
        public static final LeadIngotItem LEAD_INGOT_ITEM = new LeadIngotItem(
                        new Item.Settings().group(ItemGroup.MISC));
        public static final WarpStoneItem WARP_STONE_ITEM = new WarpStoneItem(
                        new Item.Settings().group(ItemGroup.MISC));
        public static final CutlassItem CUTLASS_ITEM = new CutlassItem(ExtraToolMaterial.LEAD, 2, -3.2F,
                        new Item.Settings().group(ItemGroup.COMBAT));
        // Armor
        public static final ArmorMaterial LEAD_LINED_ARMOR_MATERIAL = new LeadLinedArmorMaterial();
        public static final Item LEAD_LINED_HELMET = new LeadLinedArmor(LEAD_LINED_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item LEAD_LINED_CHESTPLATE = new LeadLinedArmor(LEAD_LINED_ARMOR_MATERIAL,
                        EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item LEAD_LINED_LEGGINGS = new LeadLinedArmor(LEAD_LINED_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item LEAD_LINED_BOOTS = new LeadLinedArmor(LEAD_LINED_ARMOR_MATERIAL, EquipmentSlot.FEET,
                        new Item.Settings().group(ItemGroup.COMBAT));

        public static void init() {
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_ingot"), LEAD_INGOT_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "warp_stone"), WARP_STONE_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "cutlass"), CUTLASS_ITEM);

                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_helmet"), LEAD_LINED_HELMET);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_chestplate"),
                                LEAD_LINED_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_leggings"),
                                LEAD_LINED_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_boots"), LEAD_LINED_BOOTS);
        }

}
