package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.block.entity.ReactorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegister {

    public static final Identifier REACTOR = new Identifier(Main.MODID, "reactor_block_entity");

    public static final BlockEntityType<ReactorBlockEntity> REACTOR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, REACTOR,
            FabricBlockEntityTypeBuilder.create(ReactorBlockEntity::new, BlockRegister.NUCLEAR_REACTOR).build());

    public static void init(){
        Main.LOGGER.info("Initializing BlockEntities for mod: \"" + Main.MODID + "\"");
    }

}
