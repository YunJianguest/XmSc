package com.lsp.jwt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.util.PropertyFilter;
/**
 * fastjson复杂属性过滤
 * @author lsp
 *
 */
public class ComplexPropertyPreFilter implements PropertyFilter{
	private Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();  
    //@Override  
    public boolean apply(Object source, String name, Object value) {  
        for(Entry<Class<?>, Set<String>> entry : includeMap.entrySet()) {  
            Class<?> class1 = entry.getKey();  
            if(source.getClass() == class1){  
                Set<String> fields = entry.getValue();   
                for(String field : fields) {  
                	System.out.println(field);
                    if(field.equals(name)){  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
    }  
    /**
     * 去除指定类中不需要的属性（防止循环调用的异常） 
     * @param includeMap
     */
    public ComplexPropertyPreFilter(Map<Class<?>, Set<String>> includeMap){  
        this.includeMap = includeMap;  
    }  
}
