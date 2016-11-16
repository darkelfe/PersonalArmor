package darkelfe14728.personalarmor.building;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * @author Julien Rosset
 *
 * Design Table's GUI.
 * 
 * @see DesignTable
 */
public class DesignTableGUI
    extends GuiContainer
{
    public static final int GUI_ID = 0;
    
    public static final int GUI_WIDTH  = 175;
    public static final int GUI_HEIGHT = 222;
    
    public static final int GUI_INV_OFFSET_H    = 7;
    public static final int GUI_INV_OFFSET_V    = 138;
    public static final int GUI_HOTBAR_OFFSET_H = 7;
    public static final int GUI_HOTBAR_OFFSET_V = 196;
    
    private DesignTableTE tile;
    
    public DesignTableGUI(DesignTableTE tile, IInventory playerInventory)
    {
        super(new DesignTableContainer(tile, playerInventory));
        this.tile = tile;
        
        this.xSize = GUI_WIDTH;
        this.ySize = GUI_HEIGHT;
    }

    /* 
     * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerBackgroundLayer(float, int, int)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(BuildingModule.instance.getGuiTexturePrefix() + "designTable.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    /* 
     * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerForegroundLayer(int, int)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = this.tile.getInventoryName();
        this.fontRendererObj.drawString(title, 7, 7, 16777215);     // #FF FF FF
    }
}
