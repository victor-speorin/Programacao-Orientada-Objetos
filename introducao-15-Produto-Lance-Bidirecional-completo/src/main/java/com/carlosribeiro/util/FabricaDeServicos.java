package com.carlosribeiro.util;

import java.lang.reflect.InvocationTargetException;

public class FabricaDeServicos {
    public static <T> T getServico(Class<T> tipo) {
        try {
            return tipo.getDeclaredConstructor().newInstance();
        }
        catch (InstantiationException |
               IllegalAccessException |
               InvocationTargetException |
               NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
