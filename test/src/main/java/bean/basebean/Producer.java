package bean.basebean;

/**
 * Created by paji on 16/8/18
 */
public class Producer<T> extends AbstractConfigBean {

    private Class<T> interfaze;
    private T targetBean;

    public Class<T> getInterfaze() {
        return interfaze;
    }

    public void setInterfaze(Class<T> interfaze) {
        this.interfaze = interfaze;
    }

    public T getTargetBean() {
        return targetBean;
    }

    public void setTargetBean(T targetBean) {
        this.targetBean = targetBean;
    }


    @Override
    public String toString() {
        return "Producer{" +
                "interfaze=" + interfaze +
                ", targetBean=" + targetBean +
                '}';
    }
}
