window.onload = () => {
    console.log(sessionStorage.getItem("warrior"));
    console.log(sessionStorage.getItem("id"));
    if (sessionStorage.getItem("id") === "null"||sessionStorage.getItem("id") === null) {
            window.location = serverUrl + "/page/mainpage"
    }
}

function logout() {

    if (sessionStorage.getItem("id") !== "null") {
        sessionStorage.setItem("id", null);
        window.location =  "../page/mainpage"
        return;
    }

}