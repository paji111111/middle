package bean.datasourceinfo;

/**
 * Created by paji on 16/8/18
 */
public class DataSourceInfo<T> {

    private Class<T> interfaze;

    private String url;
    private String userName;
    private String password;
    private T ref;


    public Class<T> getInterfaze() {
        return interfaze;
    }

    public void setInterfaze(Class<T> interfaze) {
        this.interfaze = interfaze;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }


    @Override
    public String toString() {
        return "DataSourceInfo{" +
                "interfaze=" + interfaze +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ref=" + ref +
                '}';
    }
}
