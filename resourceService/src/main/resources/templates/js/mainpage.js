const serverUrl = "http://localhost:8088";
window.onload = () => {
    if (sessionStorage.getItem("id") !== "null") {
        if(sessionStorage.getItem("id") !== null){
            window.location = serverUrl + "/page/mainpageloged";
        }
    }

}