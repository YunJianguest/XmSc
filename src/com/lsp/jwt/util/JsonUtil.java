package com.lsp.jwt.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Gson������
 * @author XY
 *
 */
public class JsonUtil {
	private static Gson gson = new Gson(); 
	public static <T> String objectToJsonStr(T object) {
		return gson.toJson(object);
	}
	public static <T>String beanToJsonStr(T object){
		return gson.toJson(object);
		
	}
	public static <T> List<T> jsonToList(String jsonStr, Type type) {
		return gson.fromJson(jsonStr, type);
	}
	 
	public static <T> T jsonStrToObject(String jsonStr, Class<T> classOfT) {
		return gson.fromJson(jsonStr, classOfT);
	}
	
	public static ParameterizedType type(final Class<?> raw, final Type... args) {
		return new ParameterizedType() {
			
			@Override
			public Type getRawType() {
				return raw;
			}
			
			@Override
			public Type getOwnerType() {
				return null;
			}
			
			@Override
			public Type[] getActualTypeArguments() {
				return args;
			}
		};
	}
	
	
}
