let num = 0;
let deleteNum = 0;
let isleft = true;
let isright = false;
let isLive = true;
let endLeft = (window.screen.width - $("#box").width()) / 2;
let endRight = endLeft + $("#box").width();

$(document).keydown((event) => {
    if (isLive) {
        if (event.keyCode == 37) { // 왼쪽
            if (!isleft) {
                $("#char-img").attr('src', '/image/run-left.gif');
                isleft = true;
                isright = false;
            }
            if (endLeft < $("#char").offset().left) {
                $("#char").animate(
                    { left: '-=30px' }
                    , 30
                );
                $("#char").clearQueue();
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

let ani = () => {
    let charTarget = $("#char").offset().left + $("#char").width() / 2;
    $("#rain-ddong").append(createDdong(num, rand(10, 90)));
    let move = window.screen.height - $(`#ddong${num}`).height() * 4;
    console.log($(`#ddong-img${num}`).offset().left)
    $(`#ddong${num}`).animate({
        top: move
    }, 2000);
    if ($("#char").offset().top == $(`#ddong${num}`).offset().top + $(`#ddong${num}`).height()) {
        if ($(`#ddong${num}`).offset().left < charTarget && charTarget < ($(`#ddong${num}`).offset().left + $(`#ddong${num}`).width())) {
            isLive = false;
            $("#char-img").attr('src', '/image/ddong-die.png');
        }
    }

    num++;
}

let check = async () => {

}

let createDdong = (num, wid) => {
    return `<div id="ddong${num}" class="box-ddong" style="left:${wid}%">
                <img id="ddong-img${num}" class="full" src="/image/ddonglogo.png">
            </div>`
}

setTimeout(() => {
    setInterval(() => {
        $(`#ddong${deleteNum}`).remove();
        deleteNum++;
        if (isLive) {
            $("#ddong-score").val(`${deleteNum}`);
        }
    }, 500);
}, 2000);

setInterval(() => {
    ani();
}, 500);