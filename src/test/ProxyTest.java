package test;

import main.Proxy;
import org.junit.Test;

import java.sql.Connection;

public class ProxyTest {
    @Test
    public void test() {
        String url = "";
        String username = "";
        String password = "";
        Proxy.PooledDataSource ds = new Proxy().new PooledDataSource(url, username, password);

        // 三次获取的是同一个Connection
        try {
            Connection connection = ds.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = ds.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = ds.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}