package darkelfe14728.personalarmor.armor.material;

import darkelfe14728.personalarmor.core.registry.AbstractRegistry;

/**
 * @author Julien Rosset
 *
 */
public class MaterialRegistry
    extends AbstractRegistry<String, IMaterial>
{
    public static final IMaterial Leather = new darkelfe14728.personalarmor.armor.material.Leather();
    
    public MaterialRegistry()
    {
        super();
        
        this.register(Leather);
    }
    protected void register(IMaterial material)
    {
        this.register(material.getName(), material);
    }
}
