let hpText = $("#user-hptext").val().split("/");

let User = {
    name: $("#user-name").val(),
    power: $("#user-power").val(),
    coin: $("#user-coin").val(),
    nowHp: parseInt(hpText[0].replace(/[^0-9]/g, '')),
    maxHp: parseInt(hpText[1]),
    Skil: {
        name: "배치기",
        demage: Math.floor($("#user-power").val() * 2.5),
        usehp: 0 // 스킬 사용HP 초기화시켜놓은것.
    }
}
let isRecovery = false;

$("#btn-recovery").click(() => {
    if (isRecovery == false) {
        isRecovery = true;
        recovery();
    }
});

function rand(min, max) { // 랜덤함수.
    return Math.floor(Math.random() * (max - min)) + min;
}

let recovery = () => {
    let random = rand(1, 20)
    if (User.nowHp != User.maxHp) { // 풀피가 아닐때만 실행
        let needHp = 100 - (User.nowHp / User.maxHp * 100); // 풀피까지 필요한 hp %
        let needCoin = Math.floor(needHp * 0.5 * random); // 풀피까지 필요한 coin
        if (needCoin <= $("#user-coin").val()) {
            let subCoin = $("#user-coin").val() - needCoin; // 필요한 hp 비례한 coin값
            User.coin = subCoin;
            $("#user-coin").val(subCoin);
            $({ val: User.nowHp }).animate({ val: User.maxHp }, { // 현재 Hp~ 최대 Hp까지 순차적으로 증가하는 애니메이션
                duration: 1000,
                step: function () { // 현재 hp부터 순차적으로 증가.
                    let num = Math.floor(this.val);
                    $("#user-hptext").val(`${num}/${User.maxHp}`);
                },
                complete: function () { // 끝난숫자 넣기.
                    let num = Math.floor(this.val);
                    $("#user-hptext").val(`${num}/${User.maxHp}`);
                    User.nowHp = num;
                    isRecovery = false;
                }
            });
            $("#user-hpbar").animate({ width: "100%" }, 1000); // hpbar 천천히증가
        } else {
            $("#user-hptext").val("회복할 돈이 모자라다!!!");
            setTimeout(() => {
                $("#user-hptext").val(`체력 ${User.nowHp}/${User.maxHp}`);
                isRecovery = false;
            }, 1000);
        }
    }
}


let updateUser = async () => {
    let hpText = $("#user-hptext").val().split("/");
    let id = $("#id").val();
    let updateDto = {
        coin: parseInt($("#user-coin").val()),
        attack: parseInt($("#user-power").val()),
        maxHp: parseInt(hpText[1]),
        hp: parseInt(hpText[0]),
        html: parseInt($("#html").val().replace(/[^0-9]/g, '')),
        java: parseInt($("#java").val().replace(/[^0-9]/g, '')),
        jsp: parseInt($("#jsp").val().replace(/[^0-9]/g, '')),
        spring: parseInt($("#spring").val().replace(/[^0-9]/g, ''))
    };
    console.log(updateDto);
    let response = await fetch(`/s/user/${id}`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();
    console.log(responseParse);
}

function sleep(ms) { // sleep시간만큼 while돌려서 대기효과를줌
    const wakeUpTime = Date.now() + ms;
    while (Date.now() < wakeUpTime) { }
}

window.onbeforeunload = () => {
    updateUser();
    sleep(100);
};

let hpbarChange = () => {
    let hpPercent = User.nowHp / User.maxHp * 100;
    console.log(hpPercent);
    $("#user-hpbar").width(`${hpPercent - 2}%`);
}

hpbarChange();