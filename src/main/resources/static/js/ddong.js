let num = 0; // 응가 생성 수
let deleteNum = 0; // 응가 삭제 수
let isleft = true; // 왼쪽봄
let isright = false; // 오른쪽 봄
let isLive = true; // 살아있는지.
let endLeft = (window.screen.width - $("#box").width()) / 2; // 왼쪽 끝
let endRight = endLeft + $("#box").width(); // 오른쪽 끝

$(document).keydown((event) => {
    if (isLive) {
        if (event.keyCode == 37) { // 왼쪽
            if (!isleft) {
                $("#char-img").attr('src', '/image/run-left.gif'); // 왼쪽이미지
                isleft = true;
                isright = false;
            }
            if (endLeft < $("#char").offset().left) { // 왼쪽 끝점을 안넘어가게 함.
                $("#char").animate(
                    { left: '-=30px' }
                    , 30
                );
                $("#char").clearQueue(); // 꾸욱 눌렀을때 넘어가는 것때문에 큐를 비워줌
            }
        }
        if (event.keyCode == 39) { // 오른쪽
            if (!isright) {
                $("#char-img").attr('src', '/image/run-right.gif');
                isleft = false;
                isright = true;
            }
            if (endRight > $("#char").offset().left + $("#char").width())
                $("#char").animate(
                    { left: '+=30px' }
                    , 30
                );
            $("#char").clearQueue();
        }
    }
});

let ani = () => { // 응가 움직임
    $("#rain-ddong").append(createDdong(num, rand(8, 88))); // 응가 생성
    let ddongX = $(`#ddong${num}`).offset().left; // 응가의 x좌표
    let ddongWid = $(`#ddong${num}`).width()
    let move = $("#char").offset().top - $(`#ddong${num}`).height(); // 플레이어의 머리까지
    $(`#ddong${num}`).animate({ // 2초동안 떨어짐.
        top: move
    }, 2000, () => {
        let charTarget = $("#char").offset().left + $("#char").width() / 2; // 캐릭터의 중앙 x위치
        if (ddongX < charTarget && charTarget < ddongX + ddongWid) { // 캐릭터 죽음.
            isLive = false;
            $("#char-img").attr('src', '/image/ddong-die.png');
        }
    });
    num++; // 응가 숫자 ++
}


let createDdong = (num, wid) => { // 웅가 생성.
    return `<div id="ddong${num}" class="box-ddong" style="left:${wid}%">
                <img id="ddong-img${num}" class="full" src="/image/ddonglogo.png">
            </div>`
}

setTimeout(() => { // 2초뒤부터 응가 삭제.
    setInterval(() => { // 150ms 마다 응가 삭제.
        $(`#ddong${deleteNum}`).remove();
        deleteNum++;
        if (isLive) { // 살아있을때만 카운트 올라감.
            $("#ddong-score").val(`${deleteNum}`);
        }
    }, 150);
}, 2000);

setInterval(() => { // 150ms마다 응가 생성.
    ani();
}, 150);