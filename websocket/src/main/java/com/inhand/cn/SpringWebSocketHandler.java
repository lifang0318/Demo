package com.inhand.cn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *消息处理
 */
public class SpringWebSocketHandler extends TextWebSocketHandler {
    private static final ArrayList<WebSocketSession> users;
    private static final Logger logger;

    static {
        users = new ArrayList<WebSocketSession>();
        logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connect to the websocket success......");
        //添加用户到session
        users.add(session);
        String username = (String) session.getAttributes().get("userName");
        if (username!=null) {
            session.sendMessage(new TextMessage("用户名："+username));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("websocket transport error, connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return super.supportsPartialMessages();
    }

    // 发送信息给特定用户(LSH)
    public void sendMessageToUser(String username, TextMessage message) {
        for (WebSocketSession user:users) {
            if (user.getAttributes().get("userName").equals(username)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    //发送信息给所用用户，广播
    public void sendMessageToAllUser(TextMessage message) {
        for (WebSocketSession user:users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JMenu jMenu = new JMenu();
        final JMenuBar menuBar = new JMenuBar();
                menuBar.setName("menuBar");
                 final JMenu jmenuFile = new JMenu();
                 jmenuFile.setText("文件(F)");
                 jmenuFile.setName("menuFile");
                 menuBar.add(jmenuFile);

                 final JMenuItem newProject = new JMenuItem();
                 newProject.setName("newProject");
                 newProject.setText("新建工程");
                 jmenuFile.add(newProject);

                 final JMenu jmenuTool = new JMenu();
                 jmenuTool.setText("工具(T)");
                 jmenuTool.setName("jmenuTool");
                 menuBar.add(jmenuTool);
    }
}



