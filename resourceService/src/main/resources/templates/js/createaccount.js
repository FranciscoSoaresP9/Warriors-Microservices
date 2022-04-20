window.onload = () => {
    accountLogedChecking();

}
const submitButton = document.getElementById('submitbutton');
function accountLogedChecking() {
    if (sessionStorage.getItem("id") !== "null") {
        if (sessionStorage.getItem("id") !== null) {
            window.location = "../page/mainpageloged";
        }
    }
}

async function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const value = Object.fromEntries(data.entries());

    const jsonValue = JSON.stringify(value);

    submitButton.disabled = true;

    console.log(jsonValue);
    if (value.password === value.confirm_password) {
        document.getElementById("loader").classList.replace("loader-invisible", "loader");
        await requestSender(jsonValue,submitButton);
    };

}

async function requestSender(jsonValue) {
    await $.ajax({
            type: 'post',
            url: '../registryaccount',
            data: jsonValue,
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: (data) => {
                displayMessageToUser(data);


            },
            error: (status) => {
                alert(status.statusText);
                alert("Please try again later");
                document.location.reload(true);

            }
        }
    );
}

function displayMessageToUser(data) {

    submitButton.disabled = false;
    if (data === "") {
        alert("Please check the fields")
        document.getElementById("loader").classList.replace("loader", "loader-invisible");

    }

    alert(data);

    if (data="Fields doesn't filled"){
        document.getElementById("loader").classList.replace("loader", "loader-invisible");
        return;
    }

    if (data === "Account created") {
        window.location = "../page/login";
     return;
    }
    document.location.reload(true);
}

jQuery.validator.setDefaults({
    debug: true,
    success: function (label) {
        label.attr('id', 'valid');

    },
});
$("#myform").validate({
    rules: {
        your_email: {
            required: true,
            email: true
        },
        password: "required",
        confirm_password: {
            equalTo: "#password"
        }
    },
    messages: {
        username: {
            required: "Please enter an username"
        },
        your_email: {
            required: "Please provide an email"
        },
        password: {
            required: "Please provide a password"
        },
        confirm_password: {
            required: "Please provide a password",
            equalTo: "Wrong Password"
        }
    }
});


const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);