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
        public static final CutlassItem CUTLASS_ITEM = new CutlassItem(ExtraToolMaterial.LEAD, 2, -2F,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final DragonScaleItem DRAGON_SCALE_ITEM = new DragonScaleItem(
                        new Item.Settings().group(ItemGroup.MISC));
        public static final WarpStonePlacedItem WARP_STONE_PLACED_ITEM = new WarpStonePlacedItem(new Item.Settings());
        public static final WolfPeltItem WOLF_PELT_ITEM = new WolfPeltItem(new Item.Settings().group(ItemGroup.MISC));
        public static final LeatherStrapItem LEATHER_STRAP_ITEM = new LeatherStrapItem(
                        new Item.Settings().group(ItemGroup.MISC));
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

        public static final ArmorMaterial GOLDEN_LEAD_LINED_ARMOR_MATERIAL = new GoldenLeadLinedArmorMaterial();
        public static final Item GOLDEN_LEAD_LINED_HELMET = new GoldenLeadLinedArmor(GOLDEN_LEAD_LINED_ARMOR_MATERIAL,
                        EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item GOLDEN_LEAD_LINED_CHESTPLATE = new GoldenLeadLinedArmor(
                        GOLDEN_LEAD_LINED_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item GOLDEN_LEAD_LINED_LEGGINGS = new GoldenLeadLinedArmor(GOLDEN_LEAD_LINED_ARMOR_MATERIAL,
                        EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item GOLDEN_LEAD_LINED_BOOTS = new GoldenLeadLinedArmor(GOLDEN_LEAD_LINED_ARMOR_MATERIAL,
                        EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

        public static final ArmorMaterial DRAGON_ARMOR_MATERIAL = new DragonArmorMaterial();
        public static final Item DRAGON_HELMET = new LeadLinedArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item DRAGON_CHESTPLATE = new LeadLinedArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item DRAGON_LEGGINGS = new LeadLinedArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item DRAGON_BOOTS = new LeadLinedArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET,
                        new Item.Settings().group(ItemGroup.COMBAT));

        public static final ArmorMaterial WOLF_ARMOR_MATERIAL = new WolfArmorMaterial();
        public static final Item WOLF_HELMET = new LeadLinedArmor(WOLF_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item WOLF_CHESTPLATE = new LeadLinedArmor(WOLF_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item WOLF_LEGGINGS = new LeadLinedArmor(WOLF_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                        new Item.Settings().group(ItemGroup.COMBAT));
        public static final Item WOLF_BOOTS = new LeadLinedArmor(WOLF_ARMOR_MATERIAL, EquipmentSlot.FEET,
                        new Item.Settings().group(ItemGroup.COMBAT));

        public static void init() {
                // Items
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_ingot"), LEAD_INGOT_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "warp_stone"), WARP_STONE_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "cutlass"), CUTLASS_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_scale"), DRAGON_SCALE_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "warp_stone_placed"),
                                WARP_STONE_PLACED_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "leather_strap"), LEATHER_STRAP_ITEM);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "wolf_pelt"), WOLF_PELT_ITEM);
                // Armor
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_helmet"), LEAD_LINED_HELMET);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_chestplate"),
                                LEAD_LINED_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_leggings"),
                                LEAD_LINED_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_lined_boots"), LEAD_LINED_BOOTS);

                Registry.register(Registry.ITEM, new Identifier("cakewalk", "golden_lead_helmet"),
                                GOLDEN_LEAD_LINED_HELMET);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "golden_lead_chestplate"),
                                GOLDEN_LEAD_LINED_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "golden_lead_leggings"),
                                GOLDEN_LEAD_LINED_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "golden_lead_boots"),
                                GOLDEN_LEAD_LINED_BOOTS);

                Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_helmet"), DRAGON_HELMET);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_chestplate"), DRAGON_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_leggings"), DRAGON_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_boots"), DRAGON_BOOTS);

                Registry.register(Registry.ITEM, new Identifier("cakewalk", "wolf_helmet"), WOLF_HELMET);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "wolf_chestplate"), WOLF_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "wolf_leggings"), WOLF_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("cakewalk", "wolf_boots"), WOLF_BOOTS);
        }

}
