let stompClient = null;
let isChat = true;
let warningCount = 0;
let warning = {
    one: {
        time: 30,
        msg: `도배로인하여 채팅이 30초 동안 금지되고 경고 1회만큼 누적되었습니다.`,
        state: false
    },
    two: {
        time: 5,
        msg: `도배로인하여 채팅이 5분 동안 금지되고 경고 2회만큼 누적되었습니다.`,
        state: false
    },
    three: {
        time: 15,
        msg: `도배로인하여 채팅이 15분 동안 금지되고 경고 3회만큼 누적되었습니다.`,
        state: false
    },

};
let chatCount = 0;


$("#btn-send").click(() => {
    if (isChat && $("#msg").val() != "") {
        sendName();
        chatWarningCount();
    }
});

$("#msg").keydown((event) => {
    if (event.keyCode == 13) {
        if (isChat && $("#msg").val() != "") {
            console.log(chatCount);
            console.log(warningCount);
            sendName();
            chatWarningCount();
        }
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
    if (warningCount == 0) {
        $("#msgbox").append(` ${message} \n`);
    }
    if (warningCount == 1) {
        $("#msgbox").append(` ${warning.one.msg}} \n`);
    }
    if (warningCount == 2) {
        $("#msgbox").append(` ${warning.two.msg}} \n`);
    }
    if (warningCount == 3) {
        $("#msgbox").append(` ${warning.three.msg}} \n`);
    }
    $("#msgbox").scrollTop($("#msgbox").prop('scrollHeight'));
    warningCheck();
}

let chatWarningCount = () => { // 채팅갯수, 경고갯수 추가.
    chatCount++;
    if (chatCount == 10) {
        warningCount++;
        if (warning.one.state)
            warningCount++;
        if (warning.two.state)
            warningCount++;
    }
}

let warningCheck = () => { // 경고 상태 체크
    let second = 1000;
    let minute = 1000 * 60;
    if (warningCount == 1) {
        warning.one.state = true;
        warningTime(second * 10);
        warningCount = 0;
    }
    if (warningCount == 2) {
        warning.two.state = true;
        warningTime(second * 15);
        warningCount = 0;
    }
    if (warningCount >= 3) {
        warning.three.state = true;
        warningTime(second * 20);
        warningCount = 0;
    }
}

let warningTime = (time) => { // 채팅금지.
    isChat = false;
    setTimeout(() => {
        isChat = true;
    }, time);
}

let chatResetTime = () => { // 채팅카운트 리셋
    setInterval(() => {
        chatCount = 0;
    }, 5000);
}

let WarningResetTime = () => { // 경고카운트,상태 리셋
    let time = 1000 * 60 * 10 // 10분
    setInterval(() => {
        warningCount = 0;
        warning.one.state = false;
        warning.two.state = false;
        warning.three.state = false;
    }, time);
}

WarningResetTime();
chatResetTime();
connect();

window.onbeforeunload = () => {
    disconnect();
};