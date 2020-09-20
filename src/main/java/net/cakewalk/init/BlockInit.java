package net.cakewalk.init;

import net.cakewalk.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
  public static final LeadBlock LEAD_BLOCK = new LeadBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
  public static final LeadOreBlock LEAD_ORE_BLOCK = new LeadOreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE));

  public static void init() {
    Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_block"),
        new BlockItem(LEAD_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.BLOCK, new Identifier("cakewalk", "lead_block"), LEAD_BLOCK);
    Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_ore"),
        new BlockItem(LEAD_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.BLOCK, new Identifier("cakewalk", "lead_ore"), LEAD_ORE_BLOCK);
  }

}
