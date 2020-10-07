package net.cakewalk.init;

import net.cakewalk.entity.DireWolfEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {
  public static final EntityType<DireWolfEntity> DIRE_WOLF_ENTITY_TYPE = FabricEntityTypeBuilder
      .create(SpawnGroup.CREATURE, DireWolfEntity::new).dimensions(EntityDimensions.fixed(1.1F, 1.3F)).build();

  public static void init() {
    Registry.register(Registry.ENTITY_TYPE, new Identifier("cakewalk", "dire_wolf"), DIRE_WOLF_ENTITY_TYPE);
    FabricDefaultAttributeRegistry.register(DIRE_WOLF_ENTITY_TYPE, DireWolfEntity.createDireWolfAttributes());
    Registry.register(Registry.ITEM, new Identifier("cakewalk", "spawn_dire_wolf"),
        new SpawnEggItem(DIRE_WOLF_ENTITY_TYPE, 0, 0, new Item.Settings().group(ItemGroup.MISC)));
  }

}
