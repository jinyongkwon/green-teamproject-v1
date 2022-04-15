
$("#find-email-error").hide();
$("#find-username-error").hide();
let isFindId = false;


let valid = {
    username: {
        state: false,
    },
    email: {
        state: false,
    },
}

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

function usernameFindCheck() {
    if (valid.username.state) {
        valid.username.state = true;
        $("#find-username-error").hide();
    } else {
        valid.username.state = false;
        $("#find-username-error").show();
    }
}

function emailFindCheck() {
    if (valid.email.state) {
        valid.email.state = true;
        $("#find-email-error").hide();
    } else {
        valid.email.state = false;
        $("#find-email-error").show();
    }
}


