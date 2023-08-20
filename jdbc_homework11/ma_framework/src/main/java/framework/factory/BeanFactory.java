package framework.factory;

import framework.annotations.BeanClass;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Store;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private final Store store;
    private final Map<Class<?>, Object> beanMap = new HashMap<>();
    public BeanFactory(Store store){
        this.store = store;
    }

    public void initBeans(){
        this.store.forEach((k, v) -> {
            if (k.equals(Scanners.TypesAnnotated.name())) {
                v.forEach((key, value) -> {
                    if (key.equals(BeanClass.class.getName())) {
                        value.forEach(bean -> {
                            try {
                                Class<?> beanClass = Class.forName(bean);
                                if (!beanClass.isAnnotationPresent(Deprecated.class)) {
                                    Class<?>[] interfaces = beanClass.getInterfaces();
                                    Class<?> parentInterface = null;
                                    if (ArrayUtils.isNotEmpty(interfaces)) {
                                        if (interfaces.length == 1) {
                                            parentInterface = interfaces[0];
                                        } else {
                                            for (Class<?> anInterface : interfaces) {
                                                if (beanClass.getSimpleName().startsWith(anInterface.getSimpleName())) {
                                                    parentInterface = anInterface;
                                                }
                                            }
                                        }
                                    }
                                    Object beanImpl = beanClass.getDeclaredConstructor().newInstance();
                                    beanMap.put(parentInterface, beanImpl);
                                }
                            } catch (Exception e) {
                                System.out.println("e = " + e);
                            }
                        });
                    }
                });
            }
        });
    }

    public Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
