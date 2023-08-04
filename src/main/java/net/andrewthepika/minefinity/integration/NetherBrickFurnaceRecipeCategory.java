package net.andrewthepika.minefinity.integration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.andrewthepika.minefinity.Minefinity;
import net.andrewthepika.minefinity.block.ModBlocks;
import net.andrewthepika.minefinity.recipe.NetherBrickFurnaceRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


public class NetherBrickFurnaceRecipeCategory implements IRecipeCategory<NetherBrickFurnaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Minefinity.MOD_ID, "nether_brick_furnace_alloying");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Minefinity.MOD_ID, "textures/gui/nether_brick_furnace_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public NetherBrickFurnaceRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.NETHER_BRICK_FURNACE.get()));
    }

    @Override
    public RecipeType<NetherBrickFurnaceRecipe> getRecipeType() {
        return JEIMinefinityPlugin.NETHER_BRICK_FURNACE_ALLOYING;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Nether Brick Furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NetherBrickFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 43, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 115, 17).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 62).addItemStack(recipe.getResultItem());
    }
}
