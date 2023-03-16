package main;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * 代理模式
 * <p>
 * 为其他对象提供一种代理以控制这个对象访问
 * 通过封装一个接口，并向调用方返回相同的接口，从而让调用方在不改变任何代码的前提下增强某些功能（鉴权，延迟加载，连接池复用）
 */
public class Proxy {

    /**
     * 实现数据库连接池代理
     * <p>
     * 重写getConnection方法，从queue获取connection
     * 重写close方法，将connection放入空闲队列中
     */
    public class PooledConnectionProxy extends AbstractConnectionProxy {
        Connection target;
        Queue<PooledConnectionProxy> idleQueue;

        public PooledConnectionProxy(Queue<PooledConnectionProxy> idleQueue, Connection target) {
            this.idleQueue = idleQueue;
            this.target = target;
        }

        public void close() throws SQLException {
            System.out.println("Fake close and released to idle queue for future reuse: " + target);
            // 没有实际调用close方法，而是放入空闲队列
            idleQueue.offer(this);
        }
    }

    public class PooledDataSource implements DataSource {
        private String url;
        private String username;
        private String password;

        private Queue<PooledConnectionProxy> idleQueue = new ArrayBlockingQueue<>(100);

        public PooledDataSource(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public Connection getConnection() throws SQLException {
            // 从队列获取空闲链接
            PooledConnectionProxy connection = idleQueue.poll();
            if (connection == null) {
                // 没有空闲，打开一个新的
                connection = openNewConnection();
            }
            return connection;
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }

        private PooledConnectionProxy openNewConnection() throws SQLException {
            Connection connection = DriverManager.getConnection(url, username, password);
            return new PooledConnectionProxy(idleQueue, connection);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    public class LazyConnectionProxy extends AbstractConnectionProxy {
        private Supplier<Connection> supplier;
        private Connection target = null;

        public LazyConnectionProxy(Supplier<Connection> supplier) {
            this.supplier = supplier;
        }

        public void close() throws SQLException {
            if (target != null) {
                System.out.println("Close connection: " + target);
                super.close();
            }
        }

        @Override
        protected Connection getRealConnection() {
            if (target == null) {
                target = supplier.get();
            }
            return target;
        }
    }

    public class LazyDataSource implements DataSource {
        private String url;
        private String username;
        private String password;

        public LazyDataSource(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public Connection getConnection() throws SQLException {
            return new LazyConnectionProxy(() -> {
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    System.out.println("Open connection: " + connection);
                    return connection;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    /**
     * 抽向代理类
     * <p>
     * 代理类的作用是把Connection接口定义的方法全部实现一遍，因为Connection接口定义的方法太多了，后面编写LazyConnectionProxy只需要继承AbstractConnectionProxy，不必再把Connection接口方法再挨个实现一遍
     */
    public static class AbstractConnectionProxy implements Connection {
        // 抽向方法获取实际的Connection
        protected Connection getRealConnection() {
            return null;
        }

        // 实现Connection接口的每一个方法
        public Statement createStatement() throws SQLException {
            return getRealConnection().createStatement();
        }

        // 继承原来的方法
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return null;
        }

        public CallableStatement prepareCall(String sql) throws SQLException {
            return null;
        }

        public String nativeSQL(String sql) throws SQLException {
            return null;
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {

        }

        public boolean getAutoCommit() throws SQLException {
            return false;
        }

        public void commit() throws SQLException {

        }

        public void rollback() throws SQLException {

        }

        public void close() throws SQLException {

        }

        public boolean isClosed() throws SQLException {
            return false;
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return null;
        }

        public void setReadOnly(boolean readOnly) throws SQLException {

        }

        public boolean isReadOnly() throws SQLException {
            return false;
        }

        public void setCatalog(String catalog) throws SQLException {

        }

        public String getCatalog() throws SQLException {
            return null;
        }

        public void setTransactionIsolation(int level) throws SQLException {

        }

        public int getTransactionIsolation() throws SQLException {
            return 0;
        }

        public SQLWarning getWarnings() throws SQLException {
            return null;
        }

        public void clearWarnings() throws SQLException {

        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return null;
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

        }

        public void setHoldability(int holdability) throws SQLException {

        }

        public int getHoldability() throws SQLException {
            return 0;
        }

        public Savepoint setSavepoint() throws SQLException {
            return null;
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return null;
        }

        public void rollback(Savepoint savepoint) throws SQLException {

        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {

        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return null;
        }

        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return null;
        }

        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return null;
        }

        public Clob createClob() throws SQLException {
            return null;
        }

        public Blob createBlob() throws SQLException {
            return null;
        }

        public NClob createNClob() throws SQLException {
            return null;
        }

        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        public boolean isValid(int timeout) throws SQLException {
            return false;
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {

        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        public String getClientInfo(String name) throws SQLException {
            return null;
        }

        public Properties getClientInfo() throws SQLException {
            return null;
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return null;
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return null;
        }

        public void setSchema(String schema) throws SQLException {

        }

        public String getSchema() throws SQLException {
            return null;
        }

        public void abort(Executor executor) throws SQLException {

        }

        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        public PreparedStatement preparedStatement(String sql) throws SQLException {
            return getRealConnection().prepareStatement(sql);
        }

        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }


}
