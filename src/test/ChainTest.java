package test;

import main.Chain;

import java.math.BigDecimal;

public class ChainTest {
    public void test() {
        // 构造责任链（顺序很重要）
        Chain.HandlerChain chain = new Chain.HandlerChain();
        chain.addHandler(new Chain.ManagerHandler());
        chain.addHandler(new Chain.DirectorHandler());
        chain.addHandler(new Chain.CeoHandler());

        Chain.Request req = new Chain.Request("Alice", new BigDecimal("123.45"));
        System.out.println(chain.process(req));
    }
}