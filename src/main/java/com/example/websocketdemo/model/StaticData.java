package com.example.websocketdemo.model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class StaticData {

	private String event;
	private List<String> events;
	
	private String comm;
	private List<String> comms;
	
	public StaticData() {
		events = new ArrayList<String>();
		JSONObject configObject = new JSONObject();
		
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RESET");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "COM");
    	events.add(configObject.toJSONString());

    	// OFFLINE
    	
		configObject = new JSONObject();    	
    	configObject.put("type", "config");
    	configObject.put("condition", "condition-offline");
    	configObject.put("target", "offline");
    	configObject.put("title", "Offline");
    	configObject.put("reason", "Offline");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "COM");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RESETTING");
    	events.add(configObject.toJSONString());
    	
    	// RESET
    	
		configObject = new JSONObject();    	
    	configObject.put("type", "config");
    	configObject.put("condition", "condition-reset");
    	configObject.put("target", "reset");
    	configObject.put("title", "Reset");
    	configObject.put("reason", "Reset");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "LOCATION SETUP");
    	events.add(configObject.toJSONString());
    	
    	
    	// ONLINE

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "STARTING SEQUENCE");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "cameraa");
    	configObject.put("condition", "condition-offline");
    	configObject.put("title", "CAM: Hall C");
    	configObject.put("reason", "Offline");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "camerab");
    	configObject.put("condition", "condition-green");
    	configObject.put("title", "CAM: Main");
    	configObject.put("reason", "Online");
    	events.add(configObject.toJSONString());

    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "camerac");
    	configObject.put("condition", "condition-offline");
    	configObject.put("title", "CAM: Hall 2A");
    	configObject.put("reason", "Offline");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "camerad");
    	configObject.put("condition", "condition-green");
    	configObject.put("title", "CAM: Parking Lot 2");
    	configObject.put("reason", "Online");
    	events.add(configObject.toJSONString());
    	    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "MONITORING");
    	events.add(configObject.toJSONString());
    	    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alerta");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "Rules Monitoring");
    	configObject.put("reason", "32 Rules being monitored");
    	events.add(configObject.toJSONString());

    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertb");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "POI Monitoring");
    	configObject.put("reason", "No Current Events");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertc");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "Event Monitoring");
    	configObject.put("reason", "No Current Events");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "MONITORING");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();    	
    	configObject.put("type", "config");
    	configObject.put("target", "cona");
    	configObject.put("condition", "condition-green");
    	configObject.put("reason", "CONDITION");
    	events.add(configObject.toJSONString());
    	
    	// FILLER
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "COMPLETE");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "COM");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "EVENT MONITORING");
    	events.add(configObject.toJSONString());
    	

		// EVENT
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alerta");
    	configObject.put("condition", "condition-yellow");
    	configObject.put("title", "Rule Triggered");
    	configObject.put("reason", "Parking Lot 2 - 4 UNKNOWNS");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "POSSIBLE EVENT PROMOTION");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "COM MONITORING");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertb");
    	configObject.put("condition", "condition-orange");
    	configObject.put("title", "POI Detected");
    	configObject.put("reason", "Al Bess Identified: CAMERA Parking Lot 2: Rule #20 Triggered: Al Bess with one or more unknowns");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "POSSIBLE EVENT PROMOTION");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RULE ANALYSYS MONITORING");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "cona");
    	configObject.put("condition", "condition-orange");
    	configObject.put("reason", "EVENT PROMOTED");
    	events.add(configObject.toJSONString());

    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertc");
    	configObject.put("condition", "condition-red");
    	configObject.put("title", "Activity Alert");
    	configObject.put("reason", "Al Bess: Possible Interaction");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "cona");
    	configObject.put("condition", "condition-hot");
    	configObject.put("reason", "OBJECT DECTION - RESPONDERS DISPATCHED");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RULE ANALYSYS MONITORING");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "cona");
    	configObject.put("condition", "condition-cool");
    	configObject.put("reason", "ALL CLEAR");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alerta");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "Rules Monitoring");
    	configObject.put("reason", "32 Rules being monitored");
    	events.add(configObject.toJSONString());

    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertb");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "POI Monitoring");
    	configObject.put("reason", "No Current Events");
    	events.add(configObject.toJSONString());
    	
    	configObject = new JSONObject();
    	configObject.put("type", "config");
    	configObject.put("target", "alertc");
    	configObject.put("condition", "condition-monitoring");
    	configObject.put("title", "Event Monitoring");
    	configObject.put("reason", "No Current Events");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();    	
    	configObject.put("type", "config");
    	configObject.put("target", "cona");
    	configObject.put("condition", "condition-green");
    	configObject.put("reason", "CONDITION");
    	events.add(configObject.toJSONString());
    	
    	// END
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "END OF EVENTS");
    	events.add(configObject.toJSONString());

		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RUN COMPLETE");
    	events.add(configObject.toJSONString());
    	
		configObject = new JSONObject();
    	configObject.put("type", "comm");
    	configObject.put("text", "RESETTING");
    	events.add(configObject.toJSONString());
    	
    	
    	
	}
	
	public List<String> getEvents() {
		return events;
	}
	
}
