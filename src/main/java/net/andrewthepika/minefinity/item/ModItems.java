package net.andrewthepika.minefinity.item;

import net.andrewthepika.minefinity.Minefinity;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Minefinity.MODID);

    public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_NICKEL = ITEMS.register("raw_nickel",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
