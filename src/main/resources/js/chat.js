var wsUri = "ws://" + window.location.host + "/ws";
var websocket;
var text;
var container;
var containerOnline;
var containerNotification;
var btn;
var username1;
var username2;
var dateText;
var MSG_CHAT = "CHAT";
var MSG_ONLINE = "ONLINE";
var MSG_SEPARATOR = "||";
var MSG_LIKE = "like";
var MSG_UNLIKE = "unlike";
var MSG_VISIT = "visit";
var MSG_INIT = "INIT";
var onlines = [];
var likes = [];
var unlikes = [];
var visits = [];
var sms = [];

function init()
{
    text = document.getElementById("text");
    username1 = document.getElementById("username1");
    username2 = document.getElementById("username2");
    container = document.getElementById("chats");
    dateText = document.getElementById("date");
    containerOnline = document.getElementById('online');
    containerNotification = document.getElementById('notification');
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
    console.log(messages);
    if (messages[0] === MSG_CHAT && messages.length === 5) {

        if (sms.indexOf(messages[1] + '-' + messages[2]) < 0 && containerNotification != null) {
            sms.push(messages[1] + '-' + messages[2]);
            out = '<div class="suggestion-usd"> <img src="/public/photos/50x50.png" width="50" height="50" alt=""> <div class="sgt-text"> <h4>'+ messages[1] +'</h4> <span>new message</span></div> <span> <a href = "/' + messages[2] + '/chats/' + messages[1] + '"><i class="la la-comments"></i></a></span></div>';
            display(out, containerNotification)
        }

        if (container != null) {
            out = '<div class="main-message-box ta-right"> <div class="message-dt"> <div class="message-inner-dt"><p>'+ messages[3] + '</p></div><span>'+ messages[4] + '</span> </div> </div>';
            display(out, container);
            container.scrollTop =  container.scrollHeight;
        }

    } else if (messages[0] === MSG_VISIT && messages.length === 4 && visits.indexOf(messages[1]) < 0) {
        visits.push(messages[1]);
        out = '<div class="suggestion-usd"><img src="http://via.placeholder.com/35x35" alt=""><div class="sgt-text"><h4>'+ messages[1] + '</h4><span>'+ messages[3] +'</span></div> <span> <a href = "/' + messages[1] + '"><i class="la la-eye"></i></a></span></div>';
        display(out, containerNotification)
    } else if (messages[0] === MSG_LIKE && messages.length === 4 && likes.indexOf(messages[1]) < 0) {
        likes.push(messages[1]);
        out = '<div class="suggestion-usd"><img src="http://via.placeholder.com/35x35" alt=""><div class="sgt-text"><h4>'+ messages[1] + '</h4><span>'+ messages[3] +'</span></div> <span> <a href = "/' + messages[1] + '"><i class="la la-thumbs-up"></i></a></span></div>';
        display(out, containerNotification);
    } else if (messages[0] === MSG_UNLIKE && messages.length === 4 && unlikes.indexOf(messages[1]) < 0) {
        unlikes.push(messages[1]);
        out = '<div class="suggestion-usd"><img src="http://via.placeholder.com/35x35" alt=""><div class="sgt-text"><h4>'+ messages[1] + '</h4><span>'+ messages[3] +'</span></div> <span> <a href = "/' + messages[1] + '"><i class="la la-thumbs-down"></i></a></span></div>';
        display(out, containerNotification);
    }
}

function onclic() {
    var msg = text.value;

    var out = '<div class="main-message-box st3"> <div class="message-dt st3"> <div class="message-inner-dt"> <p>' + msg + '</p> </div><span>' + dateText.value() + '</span> </div> </div>';
    display(out, container);

    container.scrollTop =  container.scrollHeight;

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
