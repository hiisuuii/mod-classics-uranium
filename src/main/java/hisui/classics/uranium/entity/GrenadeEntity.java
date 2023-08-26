package hisui.classics.uranium.entity;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.registers.EntityRegister;
import hisui.classics.uranium.registers.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GrenadeEntity extends ThrownItemEntity {


    private LivingEntity causingEntity;

    public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, World world) {
        super((EntityType<? extends ThrownItemEntity>)entityType, world);
    }

    public GrenadeEntity(World world, LivingEntity owner) {
        super((EntityType<? extends ThrownItemEntity>)EntityRegister.GRENADE, owner, world);
        this.causingEntity = owner;
    }

    public GrenadeEntity(World world, double x, double y, double z) {
        super((EntityType<? extends ThrownItemEntity>)EntityRegister.GRENADE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegister.GRENADE;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            double d = 0.08;
            for (int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 0.0f);
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            float power = 3.0f;
            this.discard();
            this.getWorld().createExplosion((Entity)this, this.getX(), this.getY(), this.getZ(), power, false, World.ExplosionSourceType.TNT);
        }
    }
}
