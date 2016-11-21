package darkelfe14728.personalarmor.building;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;


/**
 * @author Julien Rosset
 * 
 */
public class DesignTableGuiButton<TObject>
    extends GuiButton
{
    private static final int BUTTON_WIDTH  = 18;
    private static final int BUTTON_HEIGHT = 18;

    /**
     * Internal object.
     */
    private TObject          object;

    /**
     * New Design table's Gui button.
     * 
     * @param id
     *            Button ID.
     * @param x
     *            X coordinate.
     * @param y
     *            Y coordinate.
     * @param wFactor
     *            Width factor.
     * @param hFactor
     *            Height factor.
     * @param object
     *            Internal object
     */
    public DesignTableGuiButton(int id, int x, int y, int wFactor, int hFactor, TObject object)
    {
        super(id, x, y, wFactor * DesignTableGuiButton.BUTTON_WIDTH, hFactor * DesignTableGuiButton.BUTTON_HEIGHT, "");
        this.object = object;
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {}

    public TObject getObject()
    {
        return this.object;
    }
    public void setObject(TObject object)
    {
        this.object = object;
    }
}
