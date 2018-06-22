package com.lsp.jwt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.util.PropertyFilter;
/**
 * fastjson属性过滤
 * @author lsp
 *
 */
public class ComplexOutPropertyPreFilter implements PropertyFilter{
	private Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();  
    //@Override  
    public boolean apply(Object source, String name, Object value) {  
        for(Entry<Class<?>, Set<String>> entry : includeMap.entrySet()) {  
            Class<?> class1 = entry.getKey(); 
           
            if(source.getClass() == class1){  
                Set<String> fields = entry.getValue();   
                for(String field : fields) {   
                    if(field.equals(name)){  
                        return false;  
                    }  
                }  
            }  
        }  
        return true;  
    }  
   /**
    * 检索指定类中的指定属性   
    * @param includeMap
    */
    public ComplexOutPropertyPreFilter(Map<Class<?>, Set<String>> includeMap){  
        this.includeMap = includeMap;  
    }  
}
