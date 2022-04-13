
$("#password-error").hide();
$("#username-error").hide();
$("#nickname-error").hide();

let valid = {
    username: {
        state: false,
    },
    password: {
        state: false,
    },
    nickname: {
        state: false,
    },
}

$("#password").blur(() => {
    passwordSameCheck();
});

$("#same-password").blur(() => {
    passwordSameCheck();
});

$("#username").blur(() => {
    usernameSameCheck();
});

$("#nickname").blur(() => {
    nicknameSameCheck();
});

function check() {
    if (valid.username.state && valid.password.state &&valid.nickname.state) {
        return true;
    }else{
        return false;
    }
};

async function nicknameSameCheck() {
    let nickname = $("#nickname").val();

    let response = await fetch(`/api/user/nickname-same-check?nickname=${nickname}`);
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        if (responseParse.data == true) {
            valid.nickname.state = true;
        } else {
            valid.nickname.state = false;
            $("#nickname-error").show();
        }
    } else {
        alert(responseParse.msg);
    }
}

async function usernameSameCheck() {
    let username = $("#username").val();

    let response = await fetch(`/api/user/username-same-check?username=${username}`);
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        if (responseParse.data == true) {
            valid.username.state = true;
        } else {
            valid.username.state = false;
            $("#username-error").show();
        }
    } else {
        alert(responseParse.msg);
    }
}

function passwordSameCheck() {
    let password = $("#password").val();
    let samePassword = $("#same-password").val();
    if (password === samePassword) {
        valid.password.state = true;
        $("#password-error").hide();
    } else {
        valid.password.state = false;
        $("#password-error").show();
    }
} 

