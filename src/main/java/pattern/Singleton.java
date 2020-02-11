package pattern;

/*
static
lazy init
多线程+双锁检测
枚举
 */
public class Singleton {

    private Singleton () {
    }

    private volatile static Singleton INSTANCE = null;

    public static Singleton getInstance() {
        if(null == INSTANCE){
            synchronized (Singleton.class){
                if(null == INSTANCE){
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}

