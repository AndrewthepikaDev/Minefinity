package net.andrewthepika.minefinity.block;

import net.andrewthepika.minefinity.Minefinity;
import net.andrewthepika.minefinity.block.custom.NetherBrickFurnaceBlock;
import net.andrewthepika.minefinity.item.ModCreativeModeTab;
import net.andrewthepika.minefinity.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Minefinity.MOD_ID);

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(24f).requiresCorrectToolForDrops()), ModCreativeModeTab.MINEFINITY_OVERWORLD);
    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.MINEFINITY_OVERWORLD);
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(12f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.MINEFINITY_OVERWORLD);
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.COPPER).strength(24f).requiresCorrectToolForDrops()), ModCreativeModeTab.MINEFINITY_OVERWORLD);

    public static final RegistryObject<Block> NETHER_BRICK_FURNACE = registerBlock("nether_brick_furnace", () -> new NetherBrickFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.NETHER_BRICKS).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.MINEFINITY_NETHER);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
