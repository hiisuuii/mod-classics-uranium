package hisui.classics.uranium.entity;

import hisui.classics.uranium.mixin.ExplosionGetAffectedBlocksAccessor;
import hisui.classics.uranium.registers.BlockRegister;
import hisui.classics.uranium.registers.EntityRegister;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NuclearBombEntity extends TntEntity {
    private static final TrackedData<Integer> FUSE = DataTracker.registerData(TntEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int DEFAULT_FUSE = 80;
    @Nullable
    private LivingEntity causingEntity;

    public NuclearBombEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public NuclearBombEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this((EntityType<? extends NuclearBombEntity>) EntityRegister.NUCLEAR_BOMB, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
        this.setFuse(240);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        float f = 20.0f;
        Explosion explosion = this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), f, World.ExplosionSourceType.TNT);
        List<BlockPos> affectedBlocks = ((ExplosionGetAffectedBlocksAccessor)explosion).invokeGetAffectedBlocks();
        for (BlockPos blockPos3 : affectedBlocks) {
            if (this.random.nextInt(3) != 0 || !this.getWorld().getBlockState(blockPos3).isAir() || !this.getWorld().getBlockState(blockPos3.down()).isOpaqueFullCube(this.getWorld(), blockPos3.down())) continue;
            this.getWorld().setBlockState(blockPos3, BlockRegister.NUCLEAR_WASTE.getDefaultState());
        }
    }
}
