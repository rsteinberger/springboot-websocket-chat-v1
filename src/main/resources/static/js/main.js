
var app = angular.module("appModule", []); 
//var commArea = document.querySelector('#commArea');
//var configArea = document.querySelector('#configArea');
//var cona = document.querySelector('#cona');
//var cameraa = document.querySelector('#cameraa');
//var camerab = document.querySelector('#camerab');
//var camerac = document.querySelector('#camerac');
//var camerad = document.querySelector('#camerad');

//var alertaTitle = document.querySelector('#alertaTitle');
//var alertaArea = document.querySelector('#alertaArea');

app.controller("appController", function($scope) {


	
	function setDom(login, header, page, config, detail, condition, cameras, alerts) {
		$scope.showLogin = login;
		$scope.showHeader = header;		
		$scope.showPage = page;		
		$scope.showConfig = config;
		$scope.showDetail = detail;
		$scope.showCondition = condition;
		$scope.showCameras = cameras;
		$scope.showAlerts = alerts;
	}
    setDom(true, false, false, false, false, true, true, true);

    
	/*
	* CONFIG
	*/
    
    $scope.userName = "";
	$scope.eventStreamOpen = true;
    $scope.configEvents = [];
    
	/*
	* DOM FUNCTIONS
	*/
	
	$scope.login = function() {
		console.log("OK " + $scope.userName);
	    if($scope.userName) {
	        var socket = new SockJS('/ws');
	        stompClient = Stomp.over(socket);
	        stompClient.connect({}, onConnected, onError);
	    }
	    if($scope.userName.toLowerCase() == "normal") {
		    setDom(false, true, true, false, false, true, false, true);
	    }
	    if($scope.userName.toLowerCase() == "public") {
		    setDom(false, true, true, false, false, true, false, false);
	    }	    
	    if($scope.userName.toLowerCase() == "admin") {
		    setDom(false, true, true, false, false, true, true, true);
	    }
	    
//	    else{
//		    setDom(false, true, true, false, false, true, true, true);	    	
//	    }

				
	}

	$scope.configDialog = function() {
		setDom(false, false, false, true, false, false, false, false);
	}

	$scope.updateConfig = function() {
		console.log("updateConfig = " + $scope.configBike);
		setDom(false, true, true, false, false, true, true, true);
	}
	
	$scope.dtl = function(target) {
	    setDom(false, false, false, false, true, false, false, false);
		switch(target) {
    	case "cona":
			console.log("cona");
    		break;
		
    	}

	}
	
	$scope.closeDetail = function() {
		setDom(false, true, true, false, false, true, true, true);
	}
	
    function onConnected() {
    	console.log($scope.userName.toLowerCase())
    	if($scope.userName.toLowerCase() == "admin") {
    	    stompClient.subscribe('/topic/admin', onMessageReceived);
//    	    stompClient.subscribe('/topic/public', onMessageReceived);
    	}
    	if($scope.userName.toLowerCase() == "normal") {
    	    stompClient.subscribe('/topic/normal', onMessageReceived);		
//    	    stompClient.subscribe('/topic/public', onMessageReceived);
    	}	
    	if($scope.userName.toLowerCase() == "public") {
    	    stompClient.subscribe('/topic/public', onMessageReceived);
    	}	
        // Tell your username to the server
        stompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({sender: $scope.userName, type: 'JOIN'})
        )
        
//        connectingElement.classList.add('hidden');
    }

    function onError(error) {
//        connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
//        connectingElement.style.color = 'red';
    }	    	
	
	/*
	* APP
	*/
	
    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body);
        var content = JSON.parse(message.content);
        if($scope.eventStreamOpen) {
        	if(content.type == "comm") {
                showComm(content);        		
        	}
        	if(content.type == "config") {
                showConfig(content);        		
        	}

            console.log(JSON.stringify(content))        	
        }
    }
    
    function showComm(content) {
        var messageElement = document.createElement('div');
        messageElement.innerHTML = content.text;
        commArea.appendChild(messageElement);
        commArea.scrollTop = configArea.scrollHeight;    	
      	
    }

    function showConfig(content) {
    	switch(content.target){
    		case "reset":
        		resetAll();
        		break;
    		case "offline":
    			offlineAll();
    			break;
			default:
	        	applyConfig(content);    						
    	}

        var messageElement = document.createElement('div');
        messageElement.innerHTML = content.condition + " " + content.title + " " + content.reason;
        configArea.appendChild(messageElement);
        configArea.scrollTop = configArea.scrollHeight;    	
        
    }
    
    function offlineAll() {
    	var resetConfig = {"target":"cona", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"cameraa", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerab", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerac", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerad", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alerta", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alertb", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alertc", "condition":"condition-offline", "reason":"offline", "title":"offline"}
    	applyConfig(resetConfig);
    
    }
    
    function resetAll() {
    	var resetConfig = {"target":"cona", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"cameraa", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerab", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerac", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"camerad", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alerta", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alertb", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    	var resetConfig = {"target":"alertc", "condition":"condition-reset", "reason":"resetting", "title":"resetting"}
    	applyConfig(resetConfig);
    
    }
	
    function applyConfig(config) {
    	$scope.configEvents.push(config);
    	removeConditionClasses(config.target);
    	switch(config.target) {
    	case "cona":
    		cona.classList.add(config.condition);
    		cona.innerHTML = config.reason;
    		break;
    	case "cameraa":
    		cameraa.classList.add(config.condition);
    		cameraa.innerHTML = config.title;
    		break;		
    	case "camerab":
    		camerab.classList.add(config.condition);
    		camerab.innerHTML = config.title;
    		break;
    	case "camerac":
    		camerac.classList.add(config.condition);
    		camerac.innerHTML = config.title;
    		break;    	
    	case "camerad":
    		camerad.classList.add(config.condition);
    		camerad.innerHTML = config.title;
    		break;
    	case "alerta":
    		alerta.classList.add(config.condition);
    		alertaTitle.innerHTML = config.title;
    		alertaArea.innerHTML = config.reason;
    		break;
    	case "alertb":
    		alertb.classList.add(config.condition);
    		alertbTitle.innerHTML = config.title;
    		alertbArea.innerHTML = config.reason;
    		break;
    	case "alertc":
    		alertc.classList.add(config.condition);
    		alertcTitle.innerHTML = config.title;
    		alertcArea.innerHTML = config.reason;
    		break;
    	}
    	
    	
    }
    
    function removeConditionClasses(target) {
    	document.querySelector('#' + target).classList.remove("condition-offline");
    	document.querySelector('#' + target).classList.remove("condition-yellow");
    	document.querySelector('#' + target).classList.remove("condition-orange");
    	document.querySelector('#' + target).classList.remove("condition-green");
    	document.querySelector('#' + target).classList.remove("condition-red");
    	document.querySelector('#' + target).classList.remove("condition-reset");
    	document.querySelector('#' + target).classList.remove("condition-monitoring");
    	document.querySelector('#' + target).classList.remove("condition-hot");
    	document.querySelector('#' + target).classList.remove("condition-cool");
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});


