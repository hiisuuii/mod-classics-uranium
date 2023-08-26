package hisui.classics.uranium;

import hisui.classics.uranium.client.ClientNetworkHandler;
import hisui.classics.uranium.client.ClientRendererRegister;
import hisui.classics.uranium.gui.ReactorGuiDescription;
import hisui.classics.uranium.gui.ReactorScreen;
import hisui.classics.uranium.registers.BlockRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegister.NUCLEAR_WASTE, RenderLayer.getTranslucent());
        ClientRendererRegister.init();
        HandledScreens.<ReactorGuiDescription, ReactorScreen>register(Main.REACTOR_SCREEN_HANDLER, (gui, inventory, title) -> new ReactorScreen(gui, inventory.player, title));
        ClientNetworkHandler.init();
    }
}
