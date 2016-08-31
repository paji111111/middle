package com.jason.bbfgf.testservice;

import com.jason.bbfgf.annotation.Producer;
import org.springframework.stereotype.Service;

/**
 * Created by paji on 16/8/31
 */
@Producer(HelloService.class)
@Service
public class HelloServiceImpl implements HelloService {

}
