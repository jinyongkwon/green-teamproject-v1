let stompClient = null;

$("#btn-send").click(() => {
    sendName();
});

$("#msg").keydown((event) => {
    if (event.keyCode == 13) {
        sendName();
    }
});

function connect() { // 서버 연결하고, 서버에서 메세지 전달받는 메서드.
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() { // 서버 끊는 메서드.
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function sendName() { // 메세지를 서버로 보냄.
    stompClient.send("/app/hello", {}, JSON.stringify({ 'username': $("#user-name").val(), 'msg': $("#msg").val() }));
    $("#msg").val("");
}

function showGreeting(message) { // 메세지 띄우는 메서드
    $("#msgbox").append(` ${message} \n`);
    $("#msgbox").scrollTop($("#msgbox").prop('scrollHeight'));
}

connect();

window.onbeforeunload = () => {
    disconnect();
};