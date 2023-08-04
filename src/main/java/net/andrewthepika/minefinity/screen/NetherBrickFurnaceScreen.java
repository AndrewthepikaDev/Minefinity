package net.andrewthepika.minefinity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.andrewthepika.minefinity.Minefinity;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class NetherBrickFurnaceScreen extends AbstractContainerScreen<NetherBrickFurnaceMenu>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Minefinity.MOD_ID, "textures/gui/nether_brick_furnace_gui.png");
    public NetherBrickFurnaceScreen(NetherBrickFurnaceMenu menu, Inventory inventory, Component component){
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);
    }
    private void renderProgressArrow(PoseStack pPoseStack, int x, int y){
        if(menu.isCrafting()){
            blit(pPoseStack, x + 42, y + 33, 176, 0, 75, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
