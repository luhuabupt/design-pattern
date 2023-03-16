package test;

import main.Factory;

public class FactoryTest {
    public  void test() {
        Factory.NumberFactory factory = Factory.NumberFactory.getFactory();
        Number result = factory.parse("13");
        System.out.println(result);
    }
}