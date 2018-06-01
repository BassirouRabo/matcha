var wsUri = "ws://" + window.location.host + "/ws";
var websocket;
var text;
var container;
var onlineContainer;
var btn;
var username1;
var username2;
var MSG_CHAT = "CHAT";
var MSG_ONLINE = "ONLINE";
var MSG_SEPARATOR = "||";
var MSG_INIT = "INIT";

function init()
{
    text = document.getElementById("text");
    username1 = document.getElementById("username1");
    username2 = document.getElementById("username2");
    container = document.getElementById('message');
    onlineContainer = document.getElementById('online');
    btn = document.getElementById('btn');

    if (username1 != null && username2 != null) {

        if (btn != null) btn.addEventListener("click", onclic);

        websocket = new WebSocket(wsUri + "/" + username1.value + "/" + username2.value);

        websocket.onopen = function(evt) { onOpen(evt) };
        websocket.onclose = function(evt) { onClose(evt) };
        websocket.onerror = function(evt) { onError(evt) };
        websocket.onmessage = function(evt) { onMessage(evt) };
    }
}

function onOpen(evt)
{
    websocket.send(MSG_INIT);
}

function onClose(evt)
{
    websocket.close();
}

function onError(evt)
{

    websocket.close();
}

function onMessage(msg)
{
    var messages = msg.data.split(MSG_SEPARATOR);
    var out = null;

    if (messages[0] === MSG_CHAT && messages.length === 3) {
        out = '<div class="main-message-box ta-right"> <div class="message-dt"> <div class="message-inner-dt"> <p> '+ messages[2] + '</p> </div> <span>Sat, Aug 23, 1:08 PM</span> </div> <div class="messg-usr-img"> <img src="http://via.placeholder.com/50x50" alt=""> </div> </div>';
        display(out, container);
    } else if (messages[0] === MSG_ONLINE && messages.length === 2) {
        out = '<div class="suggestion-usd"> <img src="http://via.placeholder.com/35x35" alt=""> <div class="sgt-text"> <h4>'+ messages[1] +'</h4> </div> <span> <a href = "/\' + messages[1] +\'"><i class="la la-plus"></i></a></span></div>';
        display(out, onlineContainer)
    }
}

function onclic() {
    var msg = text.value;

    var out = '<div class="main-message-box st3"> <div class="message-dt st3"> <div class="message-inner-dt"> <p>' + msg + ' </p> </div> <span>2 minutes ago</span> </div> <div class="messg-usr-img"> <img src="http://via.placeholder.com/50x50" alt=""> </div> </div>';
    display(out);

    websocket.send(msg);

    text.value = "";
}

function display(out, ctn)
{
    var pre = document.createElement("div");
    pre.innerHTML = out;
    ctn.appendChild(pre);
}

window.addEventListener("load", init, false);
