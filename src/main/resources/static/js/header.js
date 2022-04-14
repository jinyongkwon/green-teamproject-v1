let regex = /[^0-9]/g; //숫자만 추출하기 위한 정규식.

function rand(min, max) { // 랜덤함수.
    return Math.floor(Math.random() * (max - min)) + min;
}

async function coinUpdate() {
    let id = $("#id").val();
    let coinUpdateDto = {
        coin: $("#user-coin").val()
    }
    console.log(coinUpdate);
    let response = await fetch(`/user/${id}`, {
        method: "PUT",
        body: JSON.stringify(coinUpdateDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });

    let responseParse = await response.json();

}
coinUpdate();