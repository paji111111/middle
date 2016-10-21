package com.jason.myself.register;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**注册中心框架
 * Created by liuzhixin on 2016/10/21.
 */
public class RegisterFramework {

    private Map<String,ServiceConfig> serverNameToConfig = new ConcurrentHashMap<>();

}
