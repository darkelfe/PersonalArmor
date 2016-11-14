package darkelfe14728.personalarmor.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


/**
 * @author Julien Rosset
 *
 * List of dyes colors : http://minecraft.gamepedia.com/Data_values#Dyes
 */
public enum DyeColor
{
    BLACK(0),       // Ink Sac
    RED(1),         // Rose Red
    GREEN(2),       // Cactus Green
    BROWN(3),       // Cocoa Beans
    BLUE(4),        // Lapis Lazuli
    PURPLE(5),
    CYAN(6),
    LIGHT_GRAY(7),
    GRAY(8),
    PINK(9),
    LIME(10),
    YELLOW(11),     // Dandelion Yellow
    LIGHT_BLUE(12),
    MAGENTA(13),
    ORANGE(14),
    WHITE(15);      // Bone Meal
    
    private int metadata;
    DyeColor(int metadata)
    {
        this.metadata = metadata;
    }
    
    public int getMetadata()
    {
        return this.metadata;
    }

    public ItemStack getItem()
    {
        return new ItemStack(Items.dye, 1, this.metadata);
    }
}
