package darkelfe14728.personalarmor.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

/**
 * @author Julien Rosset
 *
 * Utilities functions.
 */
public abstract class Utils
{
    @SuppressWarnings("unchecked")
    public static <TType> LinkedList<TType> getClassStaticElements(Class<?> classType, Class<TType> elementType)
    {        
        LinkedList<TType> list = new LinkedList<TType>();
        for(Field field : classType.getDeclaredFields())
        {
            if(Modifier.isStatic(field.getModifiers()))
            {
                if(elementType.isAssignableFrom(field.getType()))
                {
                    try
                    {
                        list.add((TType)field.get(null));
                    }
                    catch(IllegalAccessException e)
                    {}
                }
            }
        }

        return list;
    }
}
