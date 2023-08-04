package net.andrewthepika.minefinity.block.entity;

import net.andrewthepika.minefinity.Minefinity;
import net.andrewthepika.minefinity.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Minefinity.MOD_ID);

    public static final RegistryObject<BlockEntityType<NetherBrickFurnaceBlockEntity>> NETHER_BRICK_FURNACE = BLOCK_ENTITIES.register("nether_brick_furnace", () -> BlockEntityType.Builder.of(NetherBrickFurnaceBlockEntity::new, ModBlocks.NETHER_BRICK_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
