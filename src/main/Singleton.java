package main;

/**
 * 单例模式
 * <p>
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton;

    private static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
