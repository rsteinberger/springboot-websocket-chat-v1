package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.model.StaticData;
import com.example.websocketdemo.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
//	@Autowired
	private StaticData staticData = new StaticData();
	
	private Boolean streamRunning = false;
	private List<UserModel> users = new ArrayList();
	
	public ChatController()  {
    	System.out.println("chatConttroller");
	}
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	System.out.println("sendMessage");
        return chatMessage;
    }
    
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println("addUser");
    	System.out.println(headerAccessor.getSessionId());

    	UserModel userModel = new UserModel();
    	userModel.setName(chatMessage.getSender());
    	userModel.setSessionId(headerAccessor.getSessionId());
    	switch(chatMessage.getSender().toLowerCase() ) {
    	  case "normal":
	    	userModel.setType(UserModel.UserType.NORMAL);
    	    break;
    	  case "admin":
  	    	userModel.setType(UserModel.UserType.ADMIN);
    	    break;
    	  case "super":
  	    	userModel.setType(UserModel.UserType.SUPER);
      	    break;
    	  default: // public
  	    	userModel.setType(UserModel.UserType.PUBLIC);
    	}

    	users.add(userModel);
    	sendConfigAndStartStream(userModel);

    	// Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    public void sendConfigAndStartStream(UserModel user) {

//    	JSONObject configObject = new JSONObject();
//    	configObject.put("eventStreamOpen", false);
//    	configObject.put("condition1", "yellow");

//        Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
    	
		Date date = new Date();
		String strTime = String.valueOf(date.getTime());

		if(user.getType().equals(UserModel.UserType.ADMIN)) {
	    	ChatMessage msg = new ChatMessage();            	
	    	msg.setType(ChatMessage.MessageType.CHAT);
	    	msg.setContent("CONFIG");
	    	msg.setSender("auto");
//	    	msg.setConfig(configObject.toJSONString());
	    	msg.setDate(strTime);
	        messagingTemplate.convertAndSend("/topic/admin", msg);                
		}
		if(user.getType().equals(UserModel.UserType.NORMAL)) {
        	ChatMessage msg = new ChatMessage();            	
        	msg.setType(ChatMessage.MessageType.CHAT);
        	msg.setContent("NORMAL msg");
        	msg.setSender("auto");
        	msg.setConfig("{'data':'normal'}");
        	msg.setDate(strTime);
        	messagingTemplate.convertAndSend("/topic/normal", msg);                
		}
		
    	if( !streamRunning && user.getType().equals(UserModel.UserType.ADMIN) ) {
    		sendStream();
    		streamRunning = true;
    	}
    	
    }
    
    public void sendStream() {    	
    	List<String> events = staticData.getEvents();
        while (true) {
	    	events.forEach(event -> {
	    		sendEvent(event);

	    	});
	    	
        }
    }

    public void sendEvent(String event) {
		users.forEach(user -> {
			System.out.println("user " + user.getName() + " " + user.getType());
			System.out.println(event);

			Date date = new Date();
			String strTime = String.valueOf(date.getTime());

        	ChatMessage msg = new ChatMessage();            	
        	msg.setType(ChatMessage.MessageType.CHAT);
        	msg.setContent(event);
        	msg.setSender("auto");
        	msg.setDate(strTime);
        	
        	if(event.contains("comm") && user.getType().equals(UserModel.UserType.NORMAL)) {
        		// do not send
        	}else {
        		if(  user.getType().equals(UserModel.UserType.ADMIN)  ) {
                    messagingTemplate.convertAndSend("/topic/admin", msg);        			
        		}
        		if(  user.getType().equals(UserModel.UserType.NORMAL)  ) {
                	messagingTemplate.convertAndSend("/topic/normal", msg);        			
        		}

        	}

            try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		});

    }
    
    public void sendEven2t(String event) {
		users.forEach(user -> {
			System.out.println("user " + user.getName() + " " + user.getType());
	
			Date date = new Date();
			String strTime = String.valueOf(date.getTime());
		
			if(user.getType().equals(UserModel.UserType.ADMIN)) {
	        	ChatMessage msg = new ChatMessage();            	
	        	msg.setType(ChatMessage.MessageType.CHAT);
	        	msg.setContent(event);
	        	msg.setSender("auto");
	        	msg.setDate(strTime);
	            messagingTemplate.convertAndSend("/topic/admin", msg);                
			}
			if(user.getType().equals(UserModel.UserType.NORMAL)) {
	        	ChatMessage msg = new ChatMessage();            	
	        	msg.setType(ChatMessage.MessageType.CHAT);
	        	msg.setContent(event);
	        	msg.setSender("auto");
	        	msg.setDate(strTime);
	        	messagingTemplate.convertAndSend("/topic/normal", msg);                
			}
            try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		});

    }

}
