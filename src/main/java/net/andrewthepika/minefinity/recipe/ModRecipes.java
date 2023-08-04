package net.andrewthepika.minefinity.recipe;

import net.andrewthepika.minefinity.Minefinity;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Minefinity.MOD_ID);

    public static final RegistryObject<RecipeSerializer<NetherBrickFurnaceRecipe>> NETHER_BRICK_FURNACE_ALLOYING_SERIALIZER = SERIALIZERS.register("nether_brick_furnace_alloying", () -> NetherBrickFurnaceRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
