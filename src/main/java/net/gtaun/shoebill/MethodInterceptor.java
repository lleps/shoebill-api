package net.gtaun.shoebill;

import java.lang.reflect.Method;

/**
 * @author Spell on 14/11/16.
 */
@FunctionalInterface
public interface MethodInterceptor<T> {
    Object intercept(T source, Method method, Object[] args);
}
