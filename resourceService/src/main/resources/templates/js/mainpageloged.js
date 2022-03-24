const serverUrl = "http://192.168.1.108:8088";
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
        window.location = serverUrl + "/page/mainpage"
        return;
    }

}