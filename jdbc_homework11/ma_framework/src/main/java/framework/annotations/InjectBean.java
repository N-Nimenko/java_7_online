package framework.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectBean { }
//Если вы хотите внедрить BeanClass в BeanClass, - объявите в нужном вам классе.
