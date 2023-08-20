package framework;

import framework.config.Config;
import framework.factory.BeanFactory;

public class FrameworkStart {
    public static void start(Class<?> mainClass){
        Config config = new Config(mainClass);
        BeanFactory beanFactory = new BeanFactory(config.getStore());
        beanFactory.initBeans();
        Config.configureBean(beanFactory.getBeanMap());
        StartApp start = new StartApp();
        start.start(beanFactory.getBeanMap());
    }
}


