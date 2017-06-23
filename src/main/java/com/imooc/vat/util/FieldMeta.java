package com.imooc.vat.util;

import java.lang.annotation.Retention;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface FieldMeta {
	
	/** 
     * 是否为序列号 
     * @return 
     */  
    boolean id() default true;  
    /** 
     * 字段名称 
     * @return 
     */  
    String name() default ""; 
    /**
     * 颜色
     * @return
     */
    String color() default"";
    
    /**
     * 是不是修改成下拉框（从哪个实体里面取值）
     * @return
     */
    boolean availability() default false;
    
    /**
     * 导出的下拉框的取值字段
     * @return
     */
    String attribute() default"";
    /** 
     * 字段描述 
     * @return 
     */  
    String description() default ""; 
    /**
     * 限制为时间类型
     */
    boolean dateType() default false;
    /**
     * 限制为数字类型
     */
    boolean isInt() default false;
    
    /**
     * 必填字段
     */
    boolean isRequisite() default true;
    
}  
