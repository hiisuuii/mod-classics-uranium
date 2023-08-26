package hisui.classics.uranium.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(Explosion.class)
public interface ExplosionGetAffectedBlocksAccessor {
    @Invoker("getAffectedBlocks")
    public List<BlockPos> invokeGetAffectedBlocks();
}
