package darkelfe14728.personalarmor.building;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


/**
 * @author Julien Rosset
 * 
 *         Design Table's GUI.
 * 
 * @see DesignTable
 */
public class AssemblingTableGui
    extends GuiContainer
{
    public static final int GUI_ID = 1;

    public static final int GUI_WIDTH  = 176;
    public static final int GUI_HEIGHT = 166;

    public static final int GUI_INV_OFFSET_H    = 7;
    public static final int GUI_INV_OFFSET_V    = 83;
    public static final int GUI_HOTBAR_OFFSET_H = 7;
    public static final int GUI_HOTBAR_OFFSET_V = 141;

    public static final int GUI_PROCESS_WIDTH          =  54;
    public static final int GUI_PROCESS_HEIGHT         =   4;
    public static final int GUI_PROCESS_EMPTY_OFFSET_H =  61;
    public static final int GUI_PROCESS_EMPTY_OFFSET_V =  39;
    public static final int GUI_PROCESS_FULL_OFFSET_H  = 176;
    public static final int GUI_PROCESS_FULL_OFFSET_V  =   0;

    private AssemblingTableTE tile;

    public AssemblingTableGui(AssemblingTableTE tile, IInventory playerInventory)
    {
        super(new AssemblingTableContainer(tile, playerInventory));
        this.tile = tile;

        this.xSize = AssemblingTableGui.GUI_WIDTH;
        this.ySize = AssemblingTableGui.GUI_HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(BuildingModule.instance.getGuiTexturePrefix() + AssemblingTable.UNLOCALIZED_NAME + ".png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        
        if(this.tile.processOutputTime > 0)
        {
            int process_length = GUI_PROCESS_WIDTH * this.tile.processTime / this.tile.processOutputTime;
            if(process_length > 0)
            {
                this.drawTexturedModalRect(
                        this.guiLeft + GUI_PROCESS_EMPTY_OFFSET_H, 
                        this.guiTop  + GUI_PROCESS_EMPTY_OFFSET_V,
                        GUI_PROCESS_FULL_OFFSET_H, 
                        GUI_PROCESS_FULL_OFFSET_V,
                        process_length, 
                        GUI_PROCESS_HEIGHT
                );
            }
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = this.tile.getInventoryName();
        title = StatCollector.translateToLocal(title);
        this.fontRendererObj.drawString(title, 7, 7, 16777215);     // #FF FF FF
    }
}
