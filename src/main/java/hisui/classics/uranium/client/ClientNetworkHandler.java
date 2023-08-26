package hisui.classics.uranium.client;

import hisui.classics.uranium.registers.PacketIdRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ClientNetworkHandler {

    public static void init(){
        ClientPlayNetworking.registerGlobalReceiver(PacketIdRegister.EXPLODE_PACKET_ID, (client, handler, buf, responseSender) -> {
            World world = client.world;
            BlockPos pos = buf.readBlockPos();

            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, (1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.7f, false);
            world.addParticle(ParticleTypes.EXPLOSION, pos.getX(), pos.getY(), pos.getZ(), 1.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.EXPLOSION_EMITTER, pos.getX(), pos.getY(), pos.getZ(), 1.0, 0.0, 0.0);
        });

    }
}
