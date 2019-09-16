package com.honeywell.finger;

import com.honeywell.finger.service.CommandService;
import com.honeywell.finger.entity.User;
import com.honeywell.finger.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    UserService userService;
    @Autowired
    CommandService commandService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("nini我我");
        user.setPermission(6);
        user.setPin("3");
        userService.saveByWeb(user);
       // commondService.save(user);



    }


    @Test
    public void test2() throws UnsupportedEncodingException {
        String encode = URLDecoder.decode("哈哈", "gb2312");
        System.out.println(encode);

    }

}
