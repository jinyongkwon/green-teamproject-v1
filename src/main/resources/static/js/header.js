let popupWidth = 900; // 팝업 크기
let popupHeight = 500;
let popupX = (window.screen.width / 2) - (popupWidth / 2); // 팝업 위치
let popupY = (window.screen.height / 2) - (popupHeight / 2);
let regex = /[^0-9]/g; //숫자만 추출하기 위한 정규식.

function rand(min, max) { // 랜덤함수.
    return Math.floor(Math.random() * (max - min)) + min;
}

async function update() {
    let id = $("#id").val();
    let updateDto = {
        coin: $("#user-coin").val(),
        // hp: $("#user-hptext").val(),
        // attack: $("#user-power").val()
    }
    console.log(updateDto);
    let response = await fetch(`/s/user/${id}`, {
        method: "PUT",
        body: JSON.stringify(updateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    // console.log(response);

    let responseParse = await response.json();
    console.log(responseParse);
    if(responseParse.code == 1){
        alert("잘 갔는데?")
    }else{
        alert("잘 못갔는데?")
    }
    
}

update();

$("#btn-chat").click(() => {
    window.open('/s/chat', '채팅', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location=no,status=no,scrollbars=yes');
});




