
package com.netcracker.edu.java.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alexey Vasiliev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IpcTestClass {

	//public enum TestType {J2SE, J2EE}

	//TestType testType() default TestType.J2SE; 
	
	boolean required() default true;
	
	int weight() default 100;
	
	boolean onlyPassedYesNo() default false; 
	
	String description() default "";
	
	int percentageOfCorrectResponsesForPassed() default 100;

}
