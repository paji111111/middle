package bean.nettty.testrpc.server;

import bean.nettty.testrpc.entity.TestViruses;
import org.springframework.stereotype.Service;

/**
 * Created by paji on 16/8/31
 */
@TestViruses(HelloService.class)
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String ccc(String msg) {
        return msg+"============="+msg;
    }
}
