window.onload = () => {
accountLogedChecking()

}

function accountLogedChecking() {
    if (sessionStorage.getItem("id") !== "null") {
        if(sessionStorage.getItem("id") !== null){
            window.location = "../page/mainpageloged";
        }
    }
}