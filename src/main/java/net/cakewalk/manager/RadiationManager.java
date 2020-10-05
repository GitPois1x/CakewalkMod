package net.cakewalk.manager;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;

public class RadiationManager {

  private int radiationLevel = 0;
  private float radiation;
  private int radiationTimer;

  public void add(int radiation) {
    this.radiationLevel = Math.min(radiation + this.radiationLevel, 20);
  }

  public void update(PlayerEntity player) {
    Difficulty difficulty = player.world.getDifficulty();
    if (this.radiation > 4.0F) {
      this.radiation -= 4.0F;
      if (difficulty != Difficulty.PEACEFUL) {
        this.radiationLevel = Math.max(this.radiationLevel - 1, 0);
      }
    }
    if (this.radiationLevel > 9) {
      ++this.radiationTimer;
      if (this.radiationTimer >= 80) {
        if (player.getHealth() > 10.0F || difficulty == Difficulty.HARD
            || player.getHealth() > 1.0F && difficulty == Difficulty.NORMAL) {
          player.damage(createDamageSource(), 1.5F);
        }
        if (this.radiationLevel == 20) {
          player.damage(createDamageSource(), 4F);
        }
        this.radiationTimer = 0;
      }
    } else {
      this.radiationTimer = 0;
    }

  }

  public void fromTag(CompoundTag tag) {
    if (tag.contains("radiationLevel", 99)) {
      this.radiationLevel = tag.getInt("radiationLevel");
      this.radiationTimer = tag.getInt("radiationTickTimer");
      this.radiation = tag.getFloat("radiation");
    }
  }

  public void toTag(CompoundTag tag) {
    tag.putInt("radiationLevel", this.radiationLevel);
    tag.putInt("radiationTickTimer", this.radiationTimer);
    tag.putFloat("radiation", this.radiation);
  }

  public int getRadiationLevel() {
    return this.radiationLevel;
  }

  public boolean isNotEmpty() {
    return this.radiationLevel == 0;
  }

  public void addRadiation(float radiation) {
    this.radiation = Math.min(this.radiation + radiation, 40.0F);
  }

  public void setRadiationLevel(int radiationLevel) {
    this.radiationLevel = radiationLevel;
  }

  public static DamageSource createDamageSource() {
    return new EntityDamageSource("radiation", null);
  }
}
