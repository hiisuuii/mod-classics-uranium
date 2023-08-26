package hisui.classics.uranium.misc;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.entity.MutantEntity;
import hisui.classics.uranium.entity.MutantSkeletonEntity;
import hisui.classics.uranium.registers.EntityRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class StatusEffectMutation extends StatusEffect {

    public StatusEffectMutation() {
        super(StatusEffectCategory.HARMFUL, 0x00FF00);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 25 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        }
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getClass() == ZombieEntity.class) {
            if (entity.getStatusEffect(this).isDurationBelow(26)) {
                MutantEntity mutantEntity = ((ZombieEntity) entity).convertTo(EntityRegister.MUTANT, true);
                mutantEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
            }
        } else if (entity.getClass() == SkeletonEntity.class) {
            if (entity.getStatusEffect(this).isDurationBelow(26)) {
                MutantSkeletonEntity mutantEntity = ((SkeletonEntity) entity).convertTo(EntityRegister.MUTANT_SKELETON, true);
                mutantEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
            }
        } else if( !(entity instanceof MutantEntity || entity instanceof MutantSkeletonEntity)) {
            entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.MUTATION), 1.0f);
        }
    }
}
