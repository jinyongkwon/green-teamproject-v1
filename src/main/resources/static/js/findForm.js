// 선언

let isFindId = false;

let valid = {
    username: {
        state: false,
    },
    email: {
        state: false,
    },
}


// 기능
$("#find-email-error").hide();
$("#find-username-error").hide();

$("#find-username").blur(() => {
    isFindId = false;
    usernameFindCheck();
});

$("#find-email").blur(() => {
    isFindId = true;
    emailFindCheck();
});


function check() {
    if (isFindId) {
        if (valid.email.state) {
            return true;
        } else {
            return false;
        }
    } else {
        if (valid.email.state && valid.username.state) {
            return true;
        } else {
            return false;
        }
    }
};

async function usernameFindCheck() {
    let username = $("#find-username").val();
    let response = await fetch(`/api/user/username-same-check?username=${username}`);
    let responseParse = await response.json();

    if(responseParse.code == 1){
    if(responseParse.data == true){ // 성공했냐? 성공했다.
        valid.username.state= false;  // 성공했으니 아이디로 보내기 가능
        $("#find-username-error").show(); // "없는 이메일입니다."    
    }else{
        valid.username.state = true; // 실패했으니 아이디 보내기 불가능.
        $("#find-username-error").hide(); // 중복되는게 없다.
    }
}else{
        alert(responseParse);
    }
}


async function emailFindCheck() {

    let email = $("#find-email").val();
    let response = await fetch(`/api/user/email-same-check?email=${email}`);
    let responseParse = await response.json();


    if(responseParse.code == 1){
        if(responseParse.data == true){ 
            valid.email.state= false; 
            $("#find-email-error").show(); 
        }else{
            valid.email.state = true; 
            $("#find-email-error").hide(); 
        }
    }else{
            alert(responseParse);
        }
    }


