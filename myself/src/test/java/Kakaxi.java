import io.netty.util.internal.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * Created by liuzhixin on 2016/10/22.
 */
public interface Kakaxi {

    int m();
    int m(int a);
    int m(Integer a);
    int m(char a);
    int m(String a);
    int m(int a,int b);
    int m(int a,char b);

}






class AA{
    public static void main(String[] args) {
        Method[] ms = Kakaxi.class.getDeclaredMethods();
        for(Method m:ms){
            System.out.print("方法名："+m.getName());
            Class[] clazzs = m.getParameterTypes();
            System.out.print("  参数类型");
            for(Class clazz : clazzs){
                System.out.print(clazz.getName()+",");
            }
            System.out.print("返回值:"+m.getReturnType().getName());
            System.out.print("\n");
        }
    }
}




