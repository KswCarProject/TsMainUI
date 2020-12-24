package com.T.T.T;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: Proguard */
public @interface Ty {
    String[] T() default {};

    String[] Tr() default {};

    Class<?> Ty() default Void.class;
}
