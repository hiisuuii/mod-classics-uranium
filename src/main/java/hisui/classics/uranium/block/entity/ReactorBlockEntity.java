package hisui.classics.uranium.block.entity;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.block.BlockNuclearReactor;
import hisui.classics.uranium.gui.ReactorGuiDescription;
import hisui.classics.uranium.misc.ModDamageTypes;
import hisui.classics.uranium.mixin.ExplosionGetAffectedBlocksAccessor;
import hisui.classics.uranium.registers.*;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.client.realms.RealmsClient;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReactorBlockEntity extends AbstractFurnaceBlockEntity implements NamedScreenHandlerFactory, PropertyDelegateHolder {

    private static Random random = null;
    int explodeDelay = 200;

    public ReactorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.REACTOR_BLOCK_ENTITY, pos, state, MiscRegister.REACTOR_RECIPE_TYPE);
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new ReactorGuiDescription(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
    }

    public static void tick1(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity) {
        if(!world.isClient()) {

            ((ReactorBlockEntity) blockEntity).explodeDelay--;
            AbstractFurnaceBlockEntity.tick(world, pos, state, blockEntity);

            if (state.get(AbstractFurnaceBlock.LIT) && ((ReactorBlockEntity) blockEntity).explodeDelay <= 0) {

                ((BlockNuclearReactor) state.getBlock()).explode(world, state, pos);
                ((ReactorBlockEntity) blockEntity).explodeDelay = 200;
//                Main.LOGGER.info(world.isClient() + " client 1 state " + state.get(AbstractFurnaceBlock.LIT));
//                Main.LOGGER.info(world.isClient() + " client 2 state " + state.get(AbstractFurnaceBlock.LIT));
            }
        }
    }

    @Override
    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        }

        Item item = fuel.getItem();
        return (fuel.isOf(ItemRegister.URANIUM_DUST) ? 200 : (fuel.isOf(ItemRegister.URANIUM_COAL) ? 600 : 0));
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 2) {
            return false;
        }
        if (slot == 1) {
            return stack.isOf(ItemRegister.URANIUM_DUST) || stack.isOf(ItemRegister.URANIUM_COAL);
        }
        return stack.isOf(Items.COAL) || stack.isOf(Items.GOLD_INGOT);
    }



    @Override
    protected Text getContainerName() {
        return Text.translatable("");
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return this.propertyDelegate;
    }
}
