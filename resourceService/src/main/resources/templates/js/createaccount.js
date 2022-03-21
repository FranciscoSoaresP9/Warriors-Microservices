var serverUrl = "http://localhost:8088";
window.onload = () => {
    console.log(sessionStorage.getItem("warrior"));
    console.log(sessionStorage.getItem("id"));
    if(sessionStorage.getItem("id") !== "null"){
        if(sessionStorage.getItem("id") !== null){
            window.location = serverUrl + "/page/mainpageloged";
        }
    }
}
async function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const value = Object.fromEntries(data.entries());

    const jsonValue = JSON.stringify(value);

    const submitButton = document.getElementById('submitbutton');
    submitButton.disabled = true;

    console.log(jsonValue);
    if (value.password === value.confirm_password) {
        document.getElementById("loader").classList.replace("loader-invisible", "loader");
        await $.ajax({
            type: 'post',
            url: serverUrl + '/account/add',
            data: jsonValue,
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: (data) => {
                if (data === "") {
                    alert("Please check the fields")
                    submitButton.disabled = false;
                    document.getElementById("loader").classList.replace("loader", "loader-invisible");
                    return;
                }

                alert(data);

                if (data === "Fields doesn't filled") {
                    submitButton.disabled = false;
                    document.getElementById("loader").classList.replace("loader", "loader-invisible");
                    return;
                }
                if (data === "Account created") {
                    window.location = serverUrl + "/page/login";
                    document.getElementById("loader").classList.replace("loader", "loader-invisible");
                    return;
                }
                submitButton.disabled = false;
                document.getElementById("loader").classList.replace("loader", "loader-invisible");
                document.location.reload(true);

            }
        });
    }
    ;

}

// just for the demos, avoids form submit
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