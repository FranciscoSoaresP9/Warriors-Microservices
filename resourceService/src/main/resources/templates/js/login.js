window.onload = () => {
    accountLogedChecking();

}

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
    document.getElementById("loader").classList.replace("loader-invisible", "loader");
    await requestSender(jsonValue);


}

async function requestSender(jsonValue) {
    await $.ajax({
        type: 'post',
        url: '../login',
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
    });
}

function displayMessageToUser(data) {
    if (data !== "") {
        sessionStorage.setItem("id", data.id);
        sessionStorage.setItem("warrior", JSON.stringify(data.warrior));
        alert("Welcome back " + data.username);
        window.location = "../page/mainpageloged";

        return;
    }
    document.getElementById("loader").classList.replace("loader", "loader-invisible");
    alert("User or password wrong");
}

const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);