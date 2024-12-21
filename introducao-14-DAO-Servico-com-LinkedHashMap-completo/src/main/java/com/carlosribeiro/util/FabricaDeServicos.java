package com.carlosribeiro.util;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class FabricaDeServicos {
    public static <T> T getServico(Class<T> tipo) {
        try {
            return (T) tipo.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
                throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
        }
    }
}
