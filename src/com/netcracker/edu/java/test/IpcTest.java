package com.netcracker.edu.java.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alexey Vasiliev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IpcTest {
	
	int mark();
		
	String[] params() default "";
	
	String failedMessage() default "Failed";
	
	abstract String methodName() default "";
        
	String testName() default "";
	
	boolean required() default true;
}
