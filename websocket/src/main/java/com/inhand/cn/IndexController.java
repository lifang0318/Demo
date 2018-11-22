package com.inhand.cn;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {


//    @Autowired
//    private UserInfoService userInfoService;

    //注入websocket
    @Bean
    public SpringWebSocketHandler getWebSocketEndpoint() {
        return new SpringWebSocketHandler();
    }
    @RequestMapping(value = "/socket", method = RequestMethod.GET)

    @ResponseBody
    public Object socket(@RequestParam(value = "msg") String msg) throws IOException {
        getWebSocketEndpoint().sendMessageToUser("LSH", new TextMessage(msg.getBytes("UTF-8")));
        return "succuss";
    }
}
