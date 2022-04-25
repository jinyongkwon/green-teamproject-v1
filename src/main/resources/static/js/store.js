let startStore = false; // 처음 시작할때만 사용하는 용도
let isFree = false;
let isFreeChoose = false;

$("#random-img").click(() => { // 일반뽑기
    if (startStore == false) {
        startStore = true;
        weaponNum();
    }
    if ($("#user-coin").val() > 1000) {
        isFreeChoose = false;
        draw();
    }
});

$("#present-img").click(() => { // 무료뽑기
    if (startStore == false) {
        startStore = true;
        weaponNum();
    }
    if (isFree == true) {
        isFreeChoose = true;
        draw();
        freeClick();
    }
});

let weaponNum = () => { // 무기 갯수 초기화
    html = parseInt($("#html").val().replace(regex, ''));
    java = parseInt($("#java").val().replace(regex, ''));
    jsp = parseInt($("#jsp").val().replace(regex, ''));
    spring = parseInt($("#spring").val().replace(regex, ''));
}

function draw() { // 뽑기
    console.log(isFree);
    if (!isFree || !isFreeChoose) {
        choose();
    }
    console.log(isFree);
    let userPower = parseInt($("#user-power").val());
    let random = rand(0, 100);
    if (random >= 50 && random < 100 == true) {
        $("#random-txt").text("꽝 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
    } else if (random >= 25 && random < 50 == true) {
        html++
        $("#html").val(`x${html}`);
        $("#random-txt").text("html")
        userPower += 1
    } else if (random >= 10 && random < 25 == true) {
        java++
        $("#java").val(`x${java}`);
        $("#random-txt").text("java")
        userPower += 10
    } else if (random >= 2 && random < 10 == true) {
        jsp++
        $("#jsp").val(`x${jsp}`);
        $("#random-txt").text("jsp")
        userPower += 30
    } else if (random >= 0 && random < 2 == true) {
        spring++
        $("#spring").val(`x${spring}`);
        $("#random-txt").text("spring bootㅊㅊㅊ")
        userPower += 50
    }
    $("#user-power").val(userPower);
    isFreeChoose = false;
}

let choose = () => { // 가격 100~1000
    let random = rand(100, 1000);
    let userCoin = parseInt(User.coin) - random;
    User.coin = userCoin;
    $("#user-coin").val(userCoin);
}

let timer = (usertime) => { // 무료뽑기
    let time = usertime;
    let min = 0;
    let sec = 0;
    let startTimer = setInterval(() => {
        min = parseInt(time / 60);
        sec = time % 60;
        $("#timer-text").val("무료 뽑기 까지 " + min + "분 " + sec + "초");
        time--
        if (time < 0) {
            clearInterval(startTimer);
            $("#timer-text").val("무료 뽑기!!");
            isTimer = false;
        }
    }, 1000);
}

let freeClick = async () => {
    let now = new Date(); // 현재 시간.
    let response = await fetch("/s/user/free-time")
    let responseParse = await response.json();
    let endTime = responseParse.data + 300000;
    let freeTime = Math.floor((endTime - now.getTime()) / 1000);
    if (now.getTime() > endTime) {
        isFree = true;
    } else {
        isFree = false;
        timer(freeTime);
    }
}

let freeCheck = async () => {
    let now = new Date(); // 현재 시간.
    let response = await fetch("/s/user/free-check")
    let responseParse = await response.json(); // 유저의 시간을 받아옴.
    let endTime = responseParse.data + 300000; // 받아온 시간 + ms기준 5분추가
    let freeTime = Math.floor((endTime - now.getTime()) / 1000); // ms를 초로 변환해서 남은시간 계산.
    if (now.getTime() > endTime) { // getTime은 1970.1.1이후의 시간을 ms로 변환한 수.
        isFree = true;
        $("#timer-text").val("무료 뽑기!!");
    } else {
        isFree = false;
        timer(freeTime); // 무료가 아닐경우 timer작동
    }
}

freeCheck();
