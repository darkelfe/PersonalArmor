package darkelfe14728.personalarmor.armor.part;


/**
 * @author Julien Rosset
 * 
 * Generic armor part.
 */
public interface IArmorPart
{
    /**
     * @return Part "name".
     */
    public String getName();

    /**
     * @return Base factor for item values.
     */
    public int getBaseFactor();
    
    /**
     * @return The number of crafting material to use in crafting.
     * @see IMaterial.getCraftingMaterial
     */
    public int getCraftingMaterialQuantity();

    //public boolean isModuleApplicable(IModule module)
}
