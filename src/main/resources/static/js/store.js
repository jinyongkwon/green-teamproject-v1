let html = 0 // html 갯수 초기화.
let java = 0 // html 갯수 초기화.
let jsp = 0 // html 갯수 초기화.
let spring = 0 // html 갯수 초기화.
let startStore = false; // 처음 시작할때만 사용하는 용도
let isTimer = false;
let isFree = false;

$("#random-img").click(() => { // 일반뽑기
    if (startStore == false) {
        startStore = true;
        weaponNum();
    }
    if ($("#user-coin").val() > 1000) {
        draw();
    }
});

$("#present-img").click(() => { // 무료뽑기
    if (startStore == false) {
        startStore = true;
        weaponNum();
    }
    if (isTimer == false) {
        isTimer = true;
        isFree = true;
        console.log("start : " + isTimer);
        draw();
        timer();
        isFree = false;
    }
});

let weaponNum = () => { // 무기 갯수 초기화
    html = parseInt($("#html").text().replace(regex, ''));
    java = parseInt($("#java").text().replace(regex, ''));
    jsp = parseInt($("#jsp").text().replace(regex, ''));
    spring = parseInt($("#spring").text().replace(regex, ''));
}

function draw() { // 뽑기
    if (!isFree) {
        choose();
    }
    console.log(isFree);
    let random = rand(0, 100);
    if (random >= 50 && random < 100 == true) {
        $("#random-txt").text("꽝 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
    } else if (random >= 25 && random < 50 == true) {
        html++
        $("#html").text(`x${html}`);
        $("#random-txt").text("html")
        powerUp();
    } else if (random >= 10 && random < 25 == true) {
        java++
        $("#java").text(`x${java}`);
        $("#random-txt").text("java")
        powerUp();
    } else if (random >= 2 && random < 10 == true) {
        jsp++
        $("#jsp").text(`x${jsp}`);
        $("#random-txt").text("jsp")
        powerUp();
    } else if (random >= 0 && random < 2 == true) {
        spring++
        $("#spring").text(`x${spring}`);
        $("#random-txt").text("spring bootㅊㅊㅊ")
        powerUp();
    }
}

let choose = () => { // 가격 100~1000
    let random = rand(100, 1000);
    let userCoin = parseInt(User.coin) - random;
    User.coin = userCoin;
    $("#user-coin").val(userCoin);
}

let powerUp = () => { // 공격력 증가
    let userPower = parseInt(User.power) + (html * 1) + (java * 10) + (jsp * 30) + (
        spring * 50
    );
    $("#user-power").val(userPower);
}

let timer = () => { // 무료뽑기
    let time = 180;
    let min = 0;
    let sec = 0;
    let startTimer = setInterval(() => {
        min = parseInt(time / 60);
        sec = time % 60;
        console.log("middle : " + isTimer);

        $("#timer-text").val("무료 뽑기 까지 " + min + "분 " + sec + "초");
        time--
        if (time < 0) {
            clearInterval(startTimer);
            console.log("end : " + isTimer);
            $("#timer-text").val("무료 뽑기!!");
            isTimer = false;
        }
    }, 1000);
}
