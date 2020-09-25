package net.cakewalk.init;

import net.cakewalk.block.*;
import net.cakewalk.block.entity.PylonBaseBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    public static final LeadBlock LEAD_BLOCK = new LeadBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
    public static final LeadOreBlock LEAD_ORE_BLOCK = new LeadOreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE));
    public static final PylonBaseBlock PYLON_BASE_BLOCK = new PylonBaseBlock(
            FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final DragonAnvilBlock DRAGON_ANVIL_BLOCK = new DragonAnvilBlock(
            FabricBlockSettings.copy(Blocks.ANVIL));
    // Block Entity
    public static final BlockEntityType<PylonBaseBlockEntity> PYLON_BASE_BLOCK_ENTITY = BlockEntityType.Builder
            .create(PylonBaseBlockEntity::new, PYLON_BASE_BLOCK).build(null);

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_block"),
                new BlockItem(LEAD_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("cakewalk", "lead_block"), LEAD_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("cakewalk", "lead_ore"),
                new BlockItem(LEAD_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("cakewalk", "lead_ore"), LEAD_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("cakewalk", "pylon_base_block"),
                new BlockItem(PYLON_BASE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("cakewalk", "pylon_base_block"), PYLON_BASE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("cakewalk", "dragon_anvil"),
                new BlockItem(DRAGON_ANVIL_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("cakewalk", "dragon_anvil"), DRAGON_ANVIL_BLOCK);
        // Block Entity
        Registry.register(Registry.BLOCK_ENTITY_TYPE, "cakewalk:pylon_base_block_entity", PYLON_BASE_BLOCK_ENTITY);
    }

}
