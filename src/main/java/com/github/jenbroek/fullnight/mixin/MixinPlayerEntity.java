package com.github.jenbroek.fullnight.mixin;

import com.github.jenbroek.fullnight.ClientFullnight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
abstract class MixinPlayerEntity extends LivingEntity {

	protected MixinPlayerEntity(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean addStatusEffect(StatusEffectInstance statusEffectInstance, @Nullable Entity entity) {
		if (ClientFullnight.ON && StatusEffects.NIGHT_VISION.matches(statusEffectInstance.getEffectType()) && this.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
			return true;
		}
		return super.addStatusEffect(statusEffectInstance, entity);
	}

}
