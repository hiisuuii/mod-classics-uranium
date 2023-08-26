package hisui.classics.uranium.entity;

import hisui.classics.uranium.registers.EntityRegister;
import hisui.classics.uranium.registers.MiscRegister;
import hisui.classics.uranium.registers.SoundsRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class MutantSkeletonEntity extends SkeletonEntity {
    public MutantSkeletonEntity(EntityType<? extends MutantSkeletonEntity> entityType, World world) {
        super((EntityType<? extends SkeletonEntity>)entityType, world);
    }

    public MutantSkeletonEntity(World world) {
        this((EntityType<? extends MutantSkeletonEntity>) EntityRegister.MUTANT_SKELETON, world);
    }

    protected boolean isAffectedByDaylight() {
        return false;
    }



    @Override
    public boolean canFreeze() {
        return false;
    }

    public boolean isConverting() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.ENTITY_MUTANT_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.ENTITY_MUTANT_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.ENTITY_MUTANT_SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15f, 1.0f);
    }

    SoundEvent getStepSound() {
        return SoundsRegister.ENTITY_MUTANT_SKELETON_STEP;
    }

    public static DefaultAttributeContainer.Builder createAbstractSkeletonAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20).add(EntityAttributes.GENERIC_MAX_HEALTH, 18);
    }
    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        }
        if (target instanceof LivingEntity) {
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(MiscRegister.MUTATION, 200), this);
        }
        return true;
    }

    @Override
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
        if (persistentProjectileEntity instanceof ArrowEntity) {
            ((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(MiscRegister.MUTATION, 100));
        }
        return persistentProjectileEntity;
    }
}