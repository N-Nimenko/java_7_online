package framework.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BeanClass { }
// Если вы хотите, чтобы жизненный цикл класса перешел к framework - объявите BeanClass в нужном вам классе.
