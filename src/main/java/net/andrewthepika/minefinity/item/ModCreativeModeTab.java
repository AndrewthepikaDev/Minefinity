package net.andrewthepika.minefinity.item;

import net.andrewthepika.minefinity.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MINEFINITY_OVERWORLD = new CreativeModeTab("minefinity_overworld") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TIN_INGOT.get());
        }
    };
    public static final CreativeModeTab MINEFINITY_NETHER = new CreativeModeTab("minefinity_nether") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.NETHER_BRICK_FURNACE.get());
        }
    };
}
