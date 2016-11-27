package darkelfe14728.personalarmor.utils;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


/**
 * @author Julien Rosset
 * 
 *         Utilities functions.
 */
public abstract class InventoryHelper
{
    /**
     * Number of slot in one line.
     */
    public static final int LINE_SIZE = 9;

    public enum Constants
    {
            HOTBAR(1, InventoryHelper.LINE_SIZE, 0, 16, 16, 2, 2),
            INVENTORY(3, InventoryHelper.LINE_SIZE, HOTBAR.SLOT_STOP + 1, 16, 16, 2, 2);

        /**
         * Number of lines.
         */
        public final int NB_LINES;
        /**
         * Number of columns.
         */
        public final int NB_COLUMNS;

        /**
         * Start slot.
         */
        public final int SLOT_START;
        /**
         * Stop / end slot.
         */
        public final int SLOT_STOP;

        /**
         * Slot width.
         */
        public final int SLOT_WIDTH;
        /**
         * Slot height
         */
        public final int SLOT_HEIGHT;

        /**
         * Horizontal space between two slots.
         */
        public final int SLOT_SPACE_H;
        /**
         * Vertical space between two slots.
         */
        public final int SLOT_SPACE_V;

        /**
         * Horizontal spacing between two slots.
         */
        public final int SLOT_OFFSET_H;
        /**
         * Vertical spacing between two slots.
         */
        public final int SLOT_OFFSET_V;

        private Constants(int lines, int columns, int start, int width, int height, int space_h, int space_v)
        {
            this.NB_LINES = lines;
            this.NB_COLUMNS = columns;

            this.SLOT_START = start;
            this.SLOT_STOP = this.SLOT_START + (this.NB_LINES * this.NB_COLUMNS) - 1;

            this.SLOT_WIDTH = width;
            this.SLOT_HEIGHT = height;

            this.SLOT_SPACE_H = space_h;
            this.SLOT_SPACE_V = space_v;

            this.SLOT_OFFSET_H = this.SLOT_WIDTH + this.SLOT_SPACE_H;
            this.SLOT_OFFSET_V = this.SLOT_HEIGHT + this.SLOT_SPACE_V;
        }
    }

    /**
     * Drop an item stack in the world.
     * 
     * @param world
     *            The world.
     * @param x
     *            X coordinate of drop.
     * @param y
     *            Y coordinate of drop.
     * @param z
     *            Z coordinate of drop.
     * @param stack
     *            The item stack to drop.
     */
    public static void dropInWorld(World world, double x, double y, double z, ItemStack stack)
    {
        if(stack == null)
            return;
        
        Random rand = new Random();

        double offset_x = rand.nextFloat() * 0.8F + 0.1F;
        double offset_y = rand.nextFloat() * 0.8F + 0.1F;
        double offset_z = rand.nextFloat() * 0.8F + 0.1F;

        while(stack.stackSize > 0)
        {
            int nb = Math.min(rand.nextInt(21) + 10, stack.stackSize);
            stack.stackSize -= nb;

            ItemStack spawn = stack.copy();
            spawn.stackSize = nb;

            EntityItem entity = new EntityItem(world, x + offset_x, y + offset_y, z + offset_z, spawn);

            double f3 = 0.05D;
            entity.motionX = rand.nextGaussian() * f3;
            entity.motionY = rand.nextGaussian() * f3 + 0.20000000298023224D;
            entity.motionZ = rand.nextGaussian() * f3;
            world.spawnEntityInWorld(entity);
        }
    }
    /**
     * Drop an generic inventory in the world.
     * NOTE : Item stacks are removed from inventory using IInventory.getStackInSlotOnClosing.
     * 
     * @param world
     *            The world.
     * @param x
     *            X coordinate of drop.
     * @param y
     *            Y coordinate of drop.
     * @param z
     *            Z coordinate of drop.
     * @param stack
     *            The inventory to drop.
     */
    public static void dropInWorld(World world, double x, double y, double z, IInventory inventory)
    {
        for(int idx = 0; idx < inventory.getSizeInventory(); idx++)
            InventoryHelper.dropInWorld(world, x, y, z, inventory.getStackInSlotOnClosing(idx));
    }
}
