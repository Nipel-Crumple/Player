package com.netcracker.edu.java.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * The class is designed to used as superclass for unit test classes 
 *   checking solutions of Java tasks against their interface.<br/>
 * The main feature: subclass can (and MUST) call {@link #getImpl()} 
 *   to access the instance of task implementation class (its name = T + "Impl")<br/>
 * The other features (getting metainformation from annotations) do not matter much: 
 *  they are is used only by a special engine generating reports on tasks and their solutions.
 * 
 * @author Alexey Vasiliev
 * @author Alexander Kharichkin?
 */
public abstract class IpccenterTest<T extends Object> {

    private Map<String, IpcTest> map2 = new HashMap<String, IpcTest>();
    private T impl = null;
    private int sumNestedAllMark = 0;
    private int sumNestedRequiredMark = 0;
    private boolean required = true;
    private int weight = 100;
    private boolean onlyPassedYesNo = false;
    private String description = "";
    private int percentageOfCorrectResponsesForPassed = 100;
    private Class<?> interf;

    protected IpccenterTest() {
        doCommonConstructor(getCaller());
    }

    /**
     * Returns true for this class or other possible "system" (parameterized) classes.
     */
     protected boolean isSystemClass(String className){
            return IpccenterTest.class.getName().equals(className);
     }
     @SuppressWarnings("unchecked")
     private Class<? extends IpccenterTest> getCaller(){
            for ( StackTraceElement s : new Throwable().getStackTrace()){
                   if(!isSystemClass(s.getClassName())){

                try {
                    Class<? extends IpccenterTest> ipcCenterClass = this.getClass().getClassLoader().loadClass(s.getClassName()).asSubclass(IpccenterTest.class);
                    return ipcCenterClass;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Can't find child class!");
    }

    @SuppressWarnings("unchecked")
    public void doCommonConstructor(Class _thisClass) {
        if (_thisClass == null) {
            throw new RuntimeException("Constructor " + IpccenterTest.class.getName() + " was called with null argument!");
        }
        ensureMethodAnnotation(_thisClass);
        ensureClassAnnotation(_thisClass);
        Type sc = _thisClass.getGenericSuperclass();
        if (sc == null) {
            throw new RuntimeException("Class " + _thisClass.getName() + " should extend " + IpccenterTest.class.getName() + "<...>!");
        }

        ParameterizedType t = null;
        try {
            t = (ParameterizedType) sc;
        } catch (ClassCastException e) {
            throw new RuntimeException("Class " + _thisClass.getName() + " should be paramterized: " + IpccenterTest.class.getName() + "<...>!");
        }

        try {
            interf = (Class) t.getActualTypeArguments()[0];
            interf.getName();//for exclude null objects;
        } catch (Exception e) {
            throw new RuntimeException("Parameterized type of " + IpccenterTest.class.getSimpleName() + " can be class only.");
        }
        try {
            if (this.getClass().getClassLoader() != null) {
                this.impl = (T) _thisClass.getClassLoader().loadClass(getImplClassName(interf)).newInstance();//Constants.IMPL
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
	protected String getImplClassName(Class taskInterface) {
		return taskInterface.getName()+"Impl";
	}

    @SuppressWarnings("unchecked")
    private void ensureClassAnnotation(Class c) {
        IpcTestClass annotation = (IpcTestClass) c.getAnnotation(IpcTestClass.class);

        if (annotation != null) {
            this.description = annotation.description();
            this.onlyPassedYesNo = annotation.onlyPassedYesNo();
            this.required = annotation.required();
            this.weight = annotation.weight();
            this.percentageOfCorrectResponsesForPassed = annotation.percentageOfCorrectResponsesForPassed();
        }

    }

    /**
     * Method returns Student implementation of this interface.
     * */
    protected T getImpl() {
        return impl;
    }

    private void ensureMethodAnnotation(Class c) {
        if (c == null) {
            return;
        }
        for (final Method m : c.getMethods()) {
            Test test = m.getAnnotation(Test.class);
            if (test == null) {
                continue;
            }
            final IpcTest ipcTest = m.getAnnotation(IpcTest.class);
            sumNestedAllMark += ipcTest.mark();
            if (ipcTest.required()) {
                sumNestedRequiredMark += ipcTest.mark();
            }

            IpcTest ipcNew = new IpcTest() {

                public String failedMessage() {
                    String s = ipcTest.failedMessage();
                    for (int i = 0; i < ipcTest.params().length; i++) {
                        s = s.replaceAll("[{]" + (i + 1) + "[}]", ipcTest.params()[i].replaceAll("\\$", "\\\\\\$"));
                    }
                    return s;
                }

                public int mark() {
                    return ipcTest.mark();
                }

                public String[] params() {
                    return ipcTest.params();
                }

                public Class<? extends Annotation> annotationType() {
                    return ipcTest.annotationType();
                }

                public String methodName() {
                    return m.getName();
                }

                public boolean required() {
                    return ipcTest.required();
                }
                
                public String testName() {
                    return ipcTest.testName().length() == 0 ? methodName() : ipcTest.testName();
                }
            };

            map2.put(ipcNew.methodName(), ipcNew);
        }
    }

	
	/**
	 * Method returns <code>Map<String, IpcTest></code> with detected annotations. 
	 * Key is a method name, value is corresponding annotation. 
	 * */
	public Map<String, IpcTest> getIpcTests2() {
        return Collections.unmodifiableMap(map2);
    }
    

    /**
     * Returns @IpcTest annotation to current invoked method. 
     * */
    public IpcTest getIpcTest() {
        // what else ?
        StackTraceElement e = new Throwable().getStackTrace()[1];
        try {
            return map2.get(e.getMethodName());
        } catch (Exception e1) {
//			throw new RuntimeException(e1);
            return null;
        }
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public int getSumNestedAllMark() {
        return sumNestedAllMark;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public int getSumNestedRequiredMark() {
        return sumNestedRequiredMark;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public String getDescription() {
        return description;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public boolean isOnlyPassedYesNo() {
        return onlyPassedYesNo;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public boolean isRequired() {
        return required;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public int getWeight() {
        return weight;
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public String getSimpleInterfaceName() {
        return impl.getClass().getSimpleName();
    }

    /**
     * This is system method and not recommended for invocation. 
     * */
    public int getPercentageOfCorrectResponsesForPassed() {
        return percentageOfCorrectResponsesForPassed;
    }        
}
