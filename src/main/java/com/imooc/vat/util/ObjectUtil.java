package com.imooc.vat.util;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
	/**
     * 转换 Object to Map
     *
     * @param obj 被转换的 Object
     * @return Map<String, Object>
     */
	public static Map<String, Object> obj2map(Object obj) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(obj, Map.class);
    }
}
