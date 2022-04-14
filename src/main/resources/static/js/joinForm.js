
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

<<<<<<< HEAD
<<<<<<< HEAD
$("#smae-password").blur(() => {
=======
$("#same-password").blur(() => {
>>>>>>> 9f0cddb7ec088dd11a5160f568d6fdce54b16e26
=======
$("#same-password").blur(() => {
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
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
<<<<<<< HEAD
<<<<<<< HEAD
    let nickname = $("#username").val();
=======
    let nickname = $("#nickname").val();
>>>>>>> 9f0cddb7ec088dd11a5160f568d6fdce54b16e26
=======
    let nickname = $("#nickname").val();
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f

    let response = await fetch(`/api/user/nickname-same-check?nickname=${nickname}`);
    let responseParse = await response.json();

    if (responseParse.code == 1) {
<<<<<<< HEAD
<<<<<<< HEAD
        if (!responseParse.data == false) {
            valid.nickname.state = false;
            $("#nickname-error").show();
        } else {
            valid.nickname.state = true;
=======
=======
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
        if (responseParse.data == true) {
            valid.nickname.state = true;
            $("#nickname-error").hide();
        } else {
            valid.nickname.state = false;
            $("#nickname-error").show();
<<<<<<< HEAD
>>>>>>> 9f0cddb7ec088dd11a5160f568d6fdce54b16e26
=======
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
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
<<<<<<< HEAD
<<<<<<< HEAD
        if (!responseParse.data == false) {
            valid.username.state = false;
            $("#username-error").show();
        } else {
            valid.username.state = true;
=======
=======
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
        if (responseParse.data == true) {
            valid.username.state = true;
            $("#username-error").hide();
        } else {
            valid.username.state = false;
            $("#username-error").show();
<<<<<<< HEAD
>>>>>>> 9f0cddb7ec088dd11a5160f568d6fdce54b16e26
=======
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
        $("#password-error").hide();
>>>>>>> 9f0cddb7ec088dd11a5160f568d6fdce54b16e26
=======
        $("#password-error").hide();
>>>>>>> b2e37735cc0dd0518617dfba24e26a88a8b9a34f
    } else {
        valid.password.state = false;
        $("#password-error").show();
    }
} 

