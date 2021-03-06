package darkelfe14728.personalarmor.building;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import darkelfe14728.personalarmor.armor.ArmorModule;
import darkelfe14728.personalarmor.armor.ArmorSchematicItem;
import darkelfe14728.personalarmor.armor.part.ArmorPartRegistry;
import darkelfe14728.personalarmor.armor.part.IArmorPart;
import darkelfe14728.personalarmor.core.network.PacketDispatcher;


/**
 * @author Julien Rosset
 * 
 *         Design Table's GUI.
 * 
 * @see DesignTable
 */
public class DesignTableGui
    extends GuiContainer
{
    public static final int GUI_ID = 0;

    public static final int GUI_WIDTH  = 176;
    public static final int GUI_HEIGHT = 222;

    public static final int GUI_INV_OFFSET_H    = 7;
    public static final int GUI_INV_OFFSET_V    = 138;
    public static final int GUI_HOTBAR_OFFSET_H = 7;
    public static final int GUI_HOTBAR_OFFSET_V = 196;

    private final DesignTableTE tile;

    public DesignTableGui(DesignTableTE tile, IInventory playerInventory)
    {
        super(new DesignTableContainer(tile, playerInventory));
        this.tile = tile;

        this.xSize = DesignTableGui.GUI_WIDTH;
        this.ySize = DesignTableGui.GUI_HEIGHT;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui()
    {
        super.initGui();

        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(0, this.guiLeft + 70, this.guiTop + 7, 2, 1, ArmorPartRegistry.Head));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(1, this.guiLeft + 70, this.guiTop + 25, 1, 2, ArmorPartRegistry.Chest));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(2, this.guiLeft + 88, this.guiTop + 25, 1, 2, ArmorPartRegistry.Back));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(3, this.guiLeft + 70, this.guiTop + 61, 2, 1, ArmorPartRegistry.Belt));

        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(4, this.guiLeft + 52, this.guiTop + 25, 1, 1, ArmorPartRegistry.ArmLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(5, this.guiLeft + 106, this.guiTop + 25, 1, 1, ArmorPartRegistry.ArmRight));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(6, this.guiLeft + 52, this.guiTop + 43, 1, 1, ArmorPartRegistry.ForearmLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(7, this.guiLeft + 106, this.guiTop + 43, 1, 1, ArmorPartRegistry.ForearmRight));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(8, this.guiLeft + 52, this.guiTop + 61, 1, 1, ArmorPartRegistry.HandLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(9, this.guiLeft + 106, this.guiTop + 61, 1, 1, ArmorPartRegistry.HandRight));

        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(10, this.guiLeft + 70, this.guiTop + 79, 1, 1, ArmorPartRegistry.LegUpLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(11, this.guiLeft + 88, this.guiTop + 79, 1, 1, ArmorPartRegistry.LegUpRight));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(12, this.guiLeft + 70, this.guiTop + 97, 1, 1, ArmorPartRegistry.LegDownLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(13, this.guiLeft + 88, this.guiTop + 97, 1, 1, ArmorPartRegistry.LegDownRight));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(14, this.guiLeft + 70, this.guiTop + 115, 1, 1, ArmorPartRegistry.FootLeft));
        this.buttonList.add(new DesignTableGuiButton<IArmorPart>(15, this.guiLeft + 88, this.guiTop + 115, 1, 1, ArmorPartRegistry.FootRight));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button instanceof DesignTableGuiButton<?>)
        {
            @SuppressWarnings("unchecked")
            IArmorPart part = ((DesignTableGuiButton<IArmorPart>)button).getObject();
            ItemStack output = ArmorSchematicItem.newItemStack(ArmorModule.Items.ArmorSchematicItem, part);

            PacketDispatcher.sendToServer(new DesignTableGuiPacket(output));
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(BuildingModule.instance.getGuiTexturePrefix() + DesignTable.UNLOCALIZED_NAME + ".png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = this.tile.getInventoryName();
        title = StatCollector.translateToLocal(title);
        this.fontRendererObj.drawString(title, 7, 7, 16777215);     // #FF FF FF
    }
}
