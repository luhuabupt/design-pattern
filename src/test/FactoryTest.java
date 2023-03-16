package test;

import main.Factory;
import org.junit.Test;

public class FactoryTest {
    @Test
    public  void test() {
        Factory.NumberFactory factory = Factory.NumberFactory.getFactory();
        Number result = factory.parse("13");
        System.out.println(result);
    }
}