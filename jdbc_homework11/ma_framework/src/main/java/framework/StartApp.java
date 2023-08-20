package framework;

import java.lang.reflect.Method;
import java.util.Map;

public class StartApp {
    public void start(Map<Class<?>, Object> beanMap) {
        beanMap.forEach((beanInterface, beanObject) -> {
            if (beanInterface.getSimpleName().equals("Controller")) {
                Method[] methods = beanObject.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().startsWith("start")) {
                        try {
                            method.invoke(beanObject);
                        } catch (Exception e) {
                            System.out.println("e = " + e);
                        }
                    }
                }
            }
        });
    }
}


