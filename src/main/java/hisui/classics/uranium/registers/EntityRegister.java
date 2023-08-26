package hisui.classics.uranium.registers;

import hisui.classics.uranium.Main;
import hisui.classics.uranium.entity.GrenadeEntity;
import hisui.classics.uranium.entity.MutantEntity;
import hisui.classics.uranium.entity.MutantSkeletonEntity;
import hisui.classics.uranium.entity.NuclearBombEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegister {

    public static final EntityType<GrenadeEntity> GRENADE = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MODID, "grenade"),
            FabricEntityTypeBuilder.<GrenadeEntity>create(SpawnGroup.MISC, GrenadeEntity::new).dimensions(new EntityDimensions(0.25f, 0.25f, true)).trackRangeChunks(4).trackedUpdateRate(10).build());
    public static final EntityType<MutantEntity> MUTANT = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MODID, "mutant"),
            FabricEntityTypeBuilder.<MutantEntity>create(SpawnGroup.MONSTER, MutantEntity::new).dimensions(new EntityDimensions(0.6f, 1.95f, true)).trackRangeChunks(8).build());
    public static final EntityType<MutantSkeletonEntity> MUTANT_SKELETON = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MODID, "mutant_skeleton"),
            FabricEntityTypeBuilder.<MutantSkeletonEntity>create(SpawnGroup.MONSTER, MutantSkeletonEntity::new).dimensions(new EntityDimensions(0.6f, 1.99f, true)).trackRangeChunks(8).build());
    public static final EntityType<NuclearBombEntity> NUCLEAR_BOMB = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MODID, "nuclear_bomb"),
            FabricEntityTypeBuilder.<NuclearBombEntity>create(SpawnGroup.MISC, NuclearBombEntity::new).dimensions(new EntityDimensions(0.98f, 0.98f, true))
                    .trackRangeChunks(10).trackedUpdateRate(10).fireImmune().build());
    public static void registerAttributes() {
    }

    public static void init(){
        registerAttributes();
        Main.LOGGER.info("Initializing Entities for mod: \"" + Main.MODID + "\"");
    }

}
