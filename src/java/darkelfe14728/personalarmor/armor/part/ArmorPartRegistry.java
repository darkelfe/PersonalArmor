package darkelfe14728.personalarmor.armor.part;

import darkelfe14728.personalarmor.core.registry.AbstractRegistry;

/**
 * @author Julien Rosset
 *
 * Armor parts registry.
 */
public class ArmorPartRegistry
    extends AbstractRegistry<String, IArmorPart>
{
    public static final IArmorPart ArmLeft      = new darkelfe14728.personalarmor.armor.part.ArmLeft();
    public static final IArmorPart ArmRight     = new darkelfe14728.personalarmor.armor.part.ArmRight();
    public static final IArmorPart Back         = new darkelfe14728.personalarmor.armor.part.Back();
    public static final IArmorPart Belt         = new darkelfe14728.personalarmor.armor.part.Belt();
    public static final IArmorPart Chest        = new darkelfe14728.personalarmor.armor.part.Chest();
    public static final IArmorPart FootLeft     = new darkelfe14728.personalarmor.armor.part.FootLeft();
    public static final IArmorPart FootRight    = new darkelfe14728.personalarmor.armor.part.FootRight();
    public static final IArmorPart ForearmLeft  = new darkelfe14728.personalarmor.armor.part.ForearmLeft();
    public static final IArmorPart ForearmRight = new darkelfe14728.personalarmor.armor.part.ForearmRight();
    public static final IArmorPart HandLeft     = new darkelfe14728.personalarmor.armor.part.HandLeft();
    public static final IArmorPart HandRight    = new darkelfe14728.personalarmor.armor.part.HandRight();
    public static final IArmorPart Head         = new darkelfe14728.personalarmor.armor.part.Head();
    public static final IArmorPart LegDownLeft  = new darkelfe14728.personalarmor.armor.part.LegDownLeft();
    public static final IArmorPart LegDownRight = new darkelfe14728.personalarmor.armor.part.LegDownRight();
    public static final IArmorPart LegUpLeft    = new darkelfe14728.personalarmor.armor.part.LegUpLeft();
    public static final IArmorPart LegUpRight   = new darkelfe14728.personalarmor.armor.part.LegUpRight();
    
    public ArmorPartRegistry()
    {
        super();
        
        this.register(ArmLeft);
        this.register(ArmRight);
        this.register(Back);
        this.register(Belt);
        this.register(Chest);
        this.register(FootLeft);
        this.register(FootRight);
        this.register(ForearmLeft);
        this.register(ForearmRight);
        this.register(HandLeft);
        this.register(HandRight);
        this.register(Head);
        this.register(LegDownLeft);
        this.register(LegDownRight);
        this.register(LegUpLeft);
        this.register(LegUpRight);
    }
    protected void register(IArmorPart part)
    {
        this.register(part.getName(), part);
    }
}
