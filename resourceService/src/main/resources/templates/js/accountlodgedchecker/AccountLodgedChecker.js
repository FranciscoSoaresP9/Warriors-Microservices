export class AccountLodgedChecker {

    constructor() {

    }

    isLodged(urlToGo) {
        if (sessionStorage.getItem("id") === "null") {
            if (sessionStorage.getItem("id") === null) {
                window.location = urlToGo;
            }
        }
    }

    isNotLodged(urlToGo) {
        if (sessionStorage.getItem("id") !== "null") {
            if (sessionStorage.getItem("id") !== null) {
                window.location = urlToGo;
            }
        }
    }
}
