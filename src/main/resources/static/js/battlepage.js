
let isAttack = false; // 공격중인지 체크
let isSkil = false; // 스킬공격중인지 체크.
let isUserAttack = false; // user가 공격인지 체크
let isMonsterAttack = false; // monster가 공격인지 체크
$("#attack-image").hide();
$("#monster-attack-image").hide();
$("#skil-image").hide();

let Monster = {
    name: $("#monster-name").val(),
    power: $("#monster-power").val()
}

function monsterMaxHp() { // 처음 순수 숫자 user hp
    let monsterHptext = $("#monster-hptext").val().split("/");
    let monsterHp = parseInt(monsterHptext[1]);
    return monsterHp;
}

function monsterHp() { // 실시간 순수 숫자 monster hp
    let monsterHptext = $("#monster-hptext").val().split("/");
    let monsterHp = parseInt(monsterHptext[0]);
    return monsterHp;
}

function demageHp() { // hpbar가 hp에 비례해서 깎임.
    if (isSkil == false) {
        if (isUserAttack == true) {
            let demage = User.power / monsterMaxHp() * 100
            console.log(demage);
            $("#monster-hpbar").animate({
                width: `-=${demage}%`
            }, 900);
        } else if (isMonsterAttack == true) {
            let demage = Monster.power / User.maxHp * 100
            console.log(demage);
            $("#user-hpbar").animate({
                width: `-=${demage}%`
            }, 900);
        }
    }
    if (isSkil == true) {
        if (isUserAttack == true) {
            let skilUseDemage = User.Skil.usehp / User.maxHp * 100
            console.log(skilUseDemage);
            $("#user-hpbar").animate({
                width: `-=${skilUseDemage}%`
            }, 900);
            let demage = User.Skil.demage / User.maxHp * 100
            console.log(demage);
            $("#monster-hpbar").animate({
                width: `-=${demage}%`
            }, 900);
        } else if (isMonsterAttack == true) {
            let demage = Monster.power / User.maxHp * 100
            console.log(demage);
            $("#user-hpbar").animate({
                width: `-=${demage}%`
            }, 900);
        }
    }
}

let monsterHpAni = (value) => {
    $({ val: monsterHp() }).animate({ val: value }, { // 현재 Hp~ 최대 Hp까지 순차적으로 증가하는 애니메이션
        duration: 1000,
        step: function () { // 현재 hp부터 순차적으로 하락.
            let num = Math.floor(this.val);
            $("#monster-hptext").val(`${num}/${monsterMaxHp()}`);
        },
        complete: function () { // 끝나는숫자 넣기.
            let num = Math.floor(this.val);
            $("#monster-hptext").val(`${num}/${monsterMaxHp()}`);
        }
    });
}

let userHpAni = (value) => {
    $({ val: User.nowHp }).animate({ val: value }, { // 현재 Hp~ 최대 Hp까지 순차적으로 증가하는 애니메이션
        duration: 1000,
        step: function () { // 현재 hp부터 순차적으로 하락.
            let num = Math.floor(this.val);
            $("#user-hptext").val(`${num}/${User.maxHp}`);
        },
        complete: function () { // 끝나는숫자 넣기.
            let num = Math.floor(this.val);
            $("#user-hptext").val(`${num}/${User.maxHp}`);
            User.nowHp = num;
        }
    });
}

function changeHpText() { // hp의 값을 변경해줌.
    if (isSkil == false) {
        if (isUserAttack == true) {
            let monsterDemageHp = monsterHp() - User.power;
            if (monsterDemageHp < 0) {
                monsterHpAni(0);
            } else {
                monsterHpAni(monsterDemageHp);
            }
        } else if (isMonsterAttack == true) {
            let userDemageHp = User.nowHp - Monster.power;
            if (userDemageHp < 0) {
                userHpAni(0);
            } else {
                userHpAni(userDemageHp);
            }
        }
    }
    if (isSkil == true) {
        if (isUserAttack == true) {
            let userUseSkilAfterHp = User.nowHp - User.Skil.usehp;
            userHpAni(userUseSkilAfterHp);
            let monsterDemageHp = monsterHp() - User.Skil.demage;
            if (monsterDemageHp < 0) {
                monsterHpAni(0);
            } else {
                monsterHpAni(monsterDemageHp);
            }
        } else if (isMonsterAttack == true) {
            let userDemageHp = User.nowHp - Monster.power;
            if (userDemageHp < 0) {
                userHpAni(0);
            } else {
                userHpAni(userDemageHp);
            }
        }
    }
}

function hitText() { // 몬스터와 캐릭터가 한 행동을 text로 뛰워줌.
    if (isSkil == false) { // 기본공격
        if (isUserAttack == true) {
            $("#textbox").prepend(
                `${User.name}이(가) ${Monster.name}에게 ${User.power} 데미지를 주었습니다.\n`
            );
            if (monsterHp() == 0) {
                $("#textbox").prepend(`${User.name}이(가) ${Monster.name}을 쓰러뜨렸습니다.\n`);
            }
        } else if (isMonsterAttack == true) {
            $("#textbox").prepend(
                `${Monster.name}이(가) ${User.name}에게 ${Monster.power} 데미지를 주었습니다.\n`
            );
            if (User.nowHp == 0) {
                $("#textbox").prepend(`${User.name}이(가) ${name}에게 패배하였습니다.\n`);
            }
        }
    }
    if (isSkil == true) { // 스킬 공격
        if (isUserAttack == true) {
            $("#textbox").prepend(
                `${User.name}이(가) HP${User.Skil.usehp}을 소모해 ${rand(1, 4)}번 스킬 ${User.Skil.name}을(를) 사용하였습니다.\n`
            );
            $("#textbox").prepend(
                `${User.name}이(가) ${Monster.name}에게 ${User.Skil.demage} 데미지를 주었습니다.\n`
            );
            if (monsterHp() == 0) {
                $("#textbox").prepend(`${User.name}이(가) ${Monster.name}을 쓰러뜨렸습니다.\n`);
            }
        } else if (isMonsterAttack == true) {
            $("#textbox").prepend(
                `${Monster.name}이(가) ${User.name}에게 ${Monster.power} 데미지를 주었습니다.\n`
            );
            if (User.nowHp == 0) {
                $("#textbox").prepend(`${User.name}이(가) ${Monster.name}에게 패배하였습니다.\n`);
            }
        }
    }
}

let userWin = () => {
    let plushp = Math.floor(User.maxHp * 0.01 * rand(10, 30));
    User.maxHp += plushp;
    $("#textbox").prepend(`${plushp}의 HP를 획득하셨습니다.\n`);
    let plusCoin = Math.floor(User.power * 0.1 * rand(10, 30));
    User.coin += plusCoin;
    $("#textbox").prepend(`${plusCoin}의 코인을 획득하셨습니다.\n`);
}

let userLose = () => {
    User.nowHp = User.maxHp / 100 * 5;
    $("#textbox").prepend(`전체체력의 5%의 체력을 가지고 부활합니다.\n`);
}

function attack() { // 몬스터와 캐릭터를 공격시킴
    User.Skil.usehp = Math.floor(User.nowHp * 0.03); // 스킬을 사용할때 현재 체력의 비례해 스킬사용
    if (User.nowHp != 0 && monsterHp() != 0) { // user의 피나 monster의 피가 0이 아닐경우에만 실행
        $("#bg").animate({ // 유저 공격 시작.
            backgroundSize: '200%'
        }, 1000);
        $("#monster-image").animate({
            width: '+=180px',
            height: '+=180px',
            right: '-=20%'
        }, 1000, () => {
            if (isAttack == true) {
                $("#attack-image").show();
                $("#attack-image").fadeOut(1000);
            }
            if (isSkil == true) {
                $("#skil-image").show();
                $("#skil-image").fadeOut(1000);
            }
            isUserAttack = true;
            $("#monster-image").attr('src', '/image/monster-demage.png');
            changeHpText();
            demageHp();
            hitText();
            $("#bg").animate({
                backgroundSize: '100%'
            }, 1000);
        });

        $("#monster-image").animate({ // 유저 공격 끝.
            width: '-=180px',
            height: '-=180px',
            right: '+=20%'
        }, 1000, () => {
            isUserAttack = false;
            if (monsterHp() != 0) { // monster의 피가 0이 아닐경우에만 실행(0이되면 공격x)
                $("#monster-image").attr('src', '/image/attack-monster.png'); // 몬스터 공격 시작.
                $("#monster-image").animate({
                    width: '+=180px',
                    height: '+=180px',
                    right: '-=20%'
                }, 1000, () => {
                    isMonsterAttack = true;
                    $("#monster-attack-image").show();
                    $("#monster-attack-image").fadeOut(3000);
                    changeHpText();
                    demageHp();
                    hitText();
                    $("#monster-image").animate({
                        width: '-=180px',
                        height: '-=180px',
                        right: '+=20%'
                    }, 1000, () => { // 몬스터 공격 끝
                        isMonsterAttack = false;
                        isAttack = false; // 공격끝
                        isSkil = false; // 스킬끝
                        if (User.nowHp == 0) {
                            userLose();
                            $("#textbox").prepend(`2초후에 페이지를 벗어납니다.\n`);
                            setTimeout(() => {
                                location.href = "/s/ready";
                            }, 2000);
                        }
                    })
                });
            } else { // 몬스터를 쓰러뜨림
                userWin();
                $("#textbox").prepend(`2초후에 페이지를 벗어납니다.\n`);
                setTimeout(() => {
                    location.href = "/s/ready";
                }, 2000);
            }
        });
    }
}

$("#btn-attack").click((event) => {
    if (isAttack == false && isSkil == false) {
        isAttack = true; // 공격시작
        attack();
    }
});

$("#btn-skil").click((event) => {
    if (isAttack == false && isSkil == false) {
        isSkil = true; // 스킬시작
        attack();
    }
});

$("#btn-run").click((event) => {
    $("#textbox").prepend(`나는 찔찔합니다 2초후에 울면서 도망갑니다.\n`);
    setTimeout(() => {
        location.href = "/s/ready"
    }, 2000)
});