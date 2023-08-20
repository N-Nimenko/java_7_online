package framework.config;

import framework.annotations.InjectBean;
import org.reflections.Reflections;
import org.reflections.Store;

import java.lang.reflect.Field;
import java.util.Map;

public class Config {
    private final Reflections scanner;
    public Config(Class<?> mainClass) {
        this.scanner = new Reflections(mainClass.getPackageName());
    }

    public static void configureBean(final Map<Class<?>, Object> beanMap) {
        beanMap.values().forEach(bean -> {
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(InjectBean.class)) {
                    Class<?> type = declaredField.getType();
                    Object injectBean = beanMap.get(type);
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(bean, injectBean);
                    } catch (IllegalAccessException e) {
                        System.out.println("e = " + e);
                    }
                }
            }
        });
    }

    public Store getStore(){
        return scanner.getStore();
    }

}
