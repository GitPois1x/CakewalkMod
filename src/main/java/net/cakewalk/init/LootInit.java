package net.cakewalk.init;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class LootInit {

  public static void init() {
    // Adding loot to loot tables
    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
      if (id.equals(new Identifier(LootTables.BURIED_TREASURE_CHEST.toString()))) {
        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder().rolls(new BinomialLootTableRange(1, 0.75f))
            .with(ItemEntry.builder(ItemInit.CUTLASS_ITEM));

        supplier.pool(poolBuilder);
      }
    });
    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
      if (id.equals(new Identifier(LootTables.SHIPWRECK_TREASURE_CHEST.toString()))) {
        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder().rolls(new BinomialLootTableRange(1, 0.2f))
            .with(ItemEntry.builder(ItemInit.CUTLASS_ITEM));

        supplier.pool(poolBuilder);
      }
    });
    // Adding loot to entities
    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
      if ("minecraft:entities/wolf".equals(id.toString())) {
        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootTableRange.create(1, 0.1f)).with(ItemEntry.builder(ItemInit.WOLF_PELT_ITEM));

        supplier.pool(poolBuilder);
      }
    });
    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
      if ("minecraft:entities/skeleton".equals(id.toString())) {
        FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootTableRange.create(1, 0.05f)).with(ItemEntry.builder(ItemInit.MARROW_BOW_ITEM));

        supplier.pool(poolBuilder);
      }
    });

  }

}
