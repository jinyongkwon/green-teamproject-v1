let popupWidth = 900; // 팝업 크기
let popupHeight = 500;
let popupX = (window.screen.width / 2) - (popupWidth / 2); // 팝업 위치
let popupY = (window.screen.height / 2) - (popupHeight / 2);

$("#btn-login").hover(() => {
    $("#btn-login").empty();
    $("#btn-login").append(`롤하러가자 ㅋㅋㅋ`);
}, () => {
    $("#btn-login").empty();
    $("#btn-login").append(`공부하러가자!!`);
});

$("#btn-find-id").click(() => {
    window.open('/id-find-form', '아이디찾기', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location = no, status = no, scrollbars = yes');
});

$("#btn-find-pwd").click(() => {
    window.open('/password-find-form', '비밀번호찾기', 'height=' + popupHeight + ', width=' + popupWidth + ', left=' + popupX + ', top=' + popupY + ',location=no,status=no,scrollbars=yes');
});