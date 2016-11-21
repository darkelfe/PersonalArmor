package darkelfe14728.personalarmor.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;


/**
 * @author Julien Rosset
 * 
 *         A generic list of static elements.
 *         Provide a function to get elements list.
 */
public abstract class ExtractableList
{
    @SuppressWarnings("unchecked")
    public static <TType> LinkedList<TType> getElements(Class<TType> type)
    {
        LinkedList<TType> list = new LinkedList<TType>();

        Class<?> obj = ExtractableList.class;
        for(Field field : obj.getDeclaredFields())
        {
            if(field.isAccessible())
            {
                if(Modifier.isStatic(field.getModifiers()))
                {
                    if(type.isAssignableFrom(field.getDeclaringClass()))
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
        }

        return list;
    }
}
