var wsUri = "ws://" + window.location.host + "/ws";
var websocket;
var text;
var container;
var btn;
var username1;
var username2;

function init()
{
    text = document.getElementById("text");
    username1 = document.getElementById("username1");
    username2 = document.getElementById("username2");
    container = document.getElementById('message');
    btn = document.getElementById('btn');
    btn.addEventListener("click", onclic);

    websocket = new WebSocket(wsUri + "/" + username1.value + "/" + username2.value);

    websocket.onopen = function(evt) { onOpen(evt) };
    websocket.onclose = function(evt) { onClose(evt) };
    websocket.onerror = function(evt) { onError(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
}

function onOpen(evt)
{
   // doSend("Monday");
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
    var out = '<div class="main-message-box ta-right"> <div class="message-dt"> <div class="message-inner-dt"> <p> '+ msg.data + '</p> </div> <span>Sat, Aug 23, 1:08 PM</span> </div> <div class="messg-usr-img"> <img src="http://via.placeholder.com/50x50" alt=""> </div> </div>';
    display(out);
}

function onclic() {
    var msg = text.value;

    var out = '<div class="main-message-box st3"> <div class="message-dt st3"> <div class="message-inner-dt"> <p>' + msg + ' </p> </div> <span>2 minutes ago</span> </div> <div class="messg-usr-img"> <img src="http://via.placeholder.com/50x50" alt=""> </div> </div>';
    display(out);

    websocket.send(msg);

    text.value = "";
}

function display(out)
{
    var pre = document.createElement("div");
    pre.innerHTML = out;
    container.appendChild(pre);
}

window.addEventListener("load", init, false);
