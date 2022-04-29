export class Loader {
    constructor() {
    }

    show() {
        document.getElementById("loader").classList.replace("loader-invisible", "loader");
    }
    hide(){
        document.getElementById("loader").classList.replace("loader", "loader-invisible");
    }
}