package net.andrewthepika.minefinity.block.entity;

import net.andrewthepika.minefinity.item.ModItems;
import net.andrewthepika.minefinity.recipe.NetherBrickFurnaceRecipe;
import net.andrewthepika.minefinity.screen.NetherBrickFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class NetherBrickFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 240;

    public NetherBrickFurnaceBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.NETHER_BRICK_FURNACE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index){
                    case 0 -> NetherBrickFurnaceBlockEntity.this.progress;
                    case 1 -> NetherBrickFurnaceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> NetherBrickFurnaceBlockEntity.this.progress = value;
                    case 1 -> NetherBrickFurnaceBlockEntity.this.maxProgress = value;
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName(){
        return Component.literal("Nether Brick Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player){
        return new NetherBrickFurnaceMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side){
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad(){
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }
    @Override
    public void invalidateCaps(){
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, NetherBrickFurnaceBlockEntity pEntity){
        if(level.isClientSide()){
            return;
        }
        if(hasRecipe(pEntity)){
            pEntity.progress++;
            setChanged(level, pos, state);
            if (pEntity.progress >= pEntity.maxProgress){
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }
    private void  resetProgress(){
        this.progress = 0;
    }
    private static void craftItem(NetherBrickFurnaceBlockEntity pEntity){
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }
        Optional<NetherBrickFurnaceRecipe> recipe = level.getRecipeManager().getRecipeFor(NetherBrickFurnaceRecipe.Type.INSTANCE, inventory, level);
        if(hasRecipe(pEntity)){
            pEntity.itemHandler.extractItem(1, 3, false);
            pEntity.itemHandler.extractItem(2, 1, false);
            pEntity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem().getItem(), pEntity.itemHandler.getStackInSlot(3).getCount()+4));
        }
    }
    private static boolean hasRecipe(NetherBrickFurnaceBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++){
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<NetherBrickFurnaceRecipe> recipe = level.getRecipeManager().getRecipeFor(NetherBrickFurnaceRecipe.Type.INSTANCE, inventory, level);
        return recipe.isPresent() && canInsertAmountInOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack){
        return inventory.getItem(3).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }
    private static boolean canInsertAmountInOutputSlot(SimpleContainer inventory){
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
