package main;

import java.math.BigDecimal;

/**
 * 工厂方法
 * <p>
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类，使一个类的实例化延迟到其子类
 * 目的：使得创建对象和使用对象是分离的，client总是引用抽象工厂和抽象产品
 * <p>
 * Product <- ProductImpl <- FactoryImpl -> main.Factory
 */
public class Factory {
    public interface NumberFactory {
        NumberFactory impl = new NumberFactoryImpl();

        static NumberFactory getFactory() {
            return impl;
        }

        Number parse(String s);
    }

    public static class NumberFactoryImpl implements NumberFactory {
        @Override
        public Number parse(String s) {
            return new BigDecimal(s);
        }
    }
}



