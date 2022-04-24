let popupWidth = 900; // 팝업 크기
let popupHeight = 500;
let popupX = (window.screen.width / 2) - (popupWidth / 2); // 팝업 위치
let popupY = (window.screen.height / 2) - (popupHeight / 2);
let regex = /[^0-9]/g; //숫자만 추출하기 위한 정규식.

function rand(min, max) { // 랜덤함수.
    return Math.floor(Math.random() * (max - min)) + min;
}

$("#btn-chat").click(() => {
    window.open('/s/chat', '채팅', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location=no,status=no,scrollbars=yes');
});



$("#btn-ranking").click(() => {
    window.open('/rank', '랭킹', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location=no,status=no,scrollbars=yes');
});


$("#btn-question").click(() => {
    window.open('/s/question/writeForm', '채팅', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location=no,status=no,scrollbars=yes');
});






