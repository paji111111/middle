import com.jason.myself.newc.reg.DBRegService;
import com.jason.myself.newc.reg.RegService;
import com.jason.myself.register.AppNode;
import com.jason.myself.register.MethodParamNode;
import com.jason.myself.register.ServiceNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuzhixin on 2016/10/28.
 */
public class DBRegServiceTest {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        String port  = "8080";
        String app  = "myApp";
        String interfaceName  = "com.hh.Service";

        List<String> method_params_list = new ArrayList<>();

        method_params_list.add("cal_char_int_int");
        method_params_list.add("cal_int_int_char");

        RegService regService = new DBRegService();
        try {
          //  regService.regist( host , port , app , interfaceName , method_params_list );
            List<AppNode> list = regService.pullAppNode();
            System.out.println(list);

            for (AppNode appNode: list) {
                 List<ServiceNode> serviceNodeList = regService.pullServiceNode(appNode.getNodeName());
                 System.out.println(serviceNodeList);
                for (ServiceNode serviceNode:serviceNodeList) {
                    List<MethodParamNode> methodParamNodeList = regService.pullMethodParamNode(serviceNode.getNodeName());
                    System.out.println(methodParamNodeList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
