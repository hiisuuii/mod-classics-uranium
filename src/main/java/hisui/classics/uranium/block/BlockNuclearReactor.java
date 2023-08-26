package hisui.classics.uranium.block;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.block.entity.ReactorBlockEntity;
import hisui.classics.uranium.mixin.ExplosionGetAffectedBlocksAccessor;
import hisui.classics.uranium.registers.BlockEntityRegister;
import hisui.classics.uranium.registers.BlockRegister;
import hisui.classics.uranium.registers.PacketIdRegister;
import hisui.classics.uranium.registers.SoundsRegister;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockNuclearReactor extends AbstractFurnaceBlock {


    Random random1;

    public BlockNuclearReactor(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ReactorBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BlockNuclearReactor.checkType(type, BlockEntityRegister.REACTOR_BLOCK_ENTITY, ReactorBlockEntity::tick1);
    }




    @Override
    public void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ReactorBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
        }
    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // You need a Block.createScreenHandlerFactory implementation that delegates to the block entity,
        // such as the one from BlockWithEntity
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
    }

    public void explode(World world, BlockState state, BlockPos pos) {
        if(!world.isClient()) {
            double wnd = world.getRandom().nextDouble();
            if(wnd < 0.06) {
                float f1 = 2.0f;
                for(ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld)world, pos)){
                    ServerPlayNetworking.send(player, PacketIdRegister.EXPLODE_PACKET_ID, PacketByteBufs.create().writeBlockPos(pos));
                }
                Explosion explosion = new Explosion(world, null, null, null, pos.getX(), pos.getY(), pos.getZ(), f1, false, Explosion.DestructionType.KEEP);
                explosion.collectBlocksAndDamageEntities();
                explosion.affectWorld(true);
                List<BlockPos> affectedBlocks = ((ExplosionGetAffectedBlocksAccessor) explosion).invokeGetAffectedBlocks();
                for (BlockPos blockPos3 : affectedBlocks) {
                    if (world.getRandom().nextInt(3) != 0 || !world.getBlockState(blockPos3).isAir() || !world.getBlockState(blockPos3.down()).isOpaqueFullCube(world, blockPos3.down()))
                        continue;
                    world.setBlockState(blockPos3, BlockRegister.NUCLEAR_WASTE.getDefaultState());
                }

            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT).booleanValue()) {
            return;
        }
        double d = (double)pos.getX() + 0.5;
        double e = pos.getY();
        double f = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.06) {
            world.playSound(d, e, f, SoundsRegister.BLOCK_REACTOR_WORKING, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        double g = 0.52;
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.62 : h;
        double j = random.nextDouble() * 7.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : h;

        for (Direction direction1 : Direction.values()) {
            BlockPos blockPos = pos.offset(direction1);
            if (world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) continue;
            Direction.Axis axis1 = direction1.getAxis();
            double e1 = axis1 == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction1.getOffsetX() : (double)random.nextFloat();
            double f1 = axis1 == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction1.getOffsetY() : (double)random.nextFloat();
            double g1 = axis1 == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction1.getOffsetZ() : (double)random.nextFloat();
            world.addParticle(new DustParticleEffect(Vec3d.unpackRgb(0x00DD00).toVector3f(), 1.0f), (double)pos.getX() + e1, (double)pos.getY() + f1, (double)pos.getZ() + g1, 0.0, 0.0, 0.0);
        }
        world.addParticle(ParticleTypes.ITEM_SLIME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }
}

