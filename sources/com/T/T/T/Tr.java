package com.T.T.T;

import com.T.T.Ty.TrG;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: Proguard */
public @interface Tr {
    String T() default "";

    TrG[] T9() default {};

    boolean Tn() default true;

    String Tr() default "";

    boolean Ty() default true;
}
