package hisui.classics.uranium.client;

import hisui.classics.uranium.entity.MutantEntityRenderer;
import hisui.classics.uranium.entity.MutantSkeletonEntityRenderer;
import hisui.classics.uranium.entity.NuclearBombEntityRenderer;
import hisui.classics.uranium.registers.EntityRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.TntEntityRenderer;

@Environment(EnvType.CLIENT)
public class ClientRendererRegister {
    public static void init() {
        EntityRendererRegistry.register(EntityRegister.GRENADE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegister.MUTANT, MutantEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegister.MUTANT_SKELETON, MutantSkeletonEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegister.NUCLEAR_BOMB, NuclearBombEntityRenderer::new);
    }
}
