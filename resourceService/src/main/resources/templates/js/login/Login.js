export class Login {
    #data;
    #loader;
    #requestSender;


    constructor() {
    }

    async login() {
        this.#loader.show();
        await this.#buildRequest();
        await this.#requestSender.sendRequest();
    }

    async #buildRequest() {
        this.#requestSender.setJsonValue(this.#transformDataIntoJsonValue());
        this.#requestSender.setUrl("../login");
        this.#requestSender.setRequestType("post");
        this.#requestSender.setSuccessRequestAction((data) => {
            console.log("ACCOUNT Logged");
            console.log(data);
            this.#successRequest(data);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
         document.location.reload(true);

        });
    }

    #buildRequestToGetWarrior(accountId) {
        console.log("Account ID");
        console.log(accountId);
        this.#requestSender.setJsonValue("");
        this.#requestSender.setUrl("../account/getwarrior/" + accountId);
        this.#requestSender.setRequestType("get");
        this.#requestSender.setSuccessRequestAction((data) => {
            console.log("WARRIOR");
            console.log(data);
            sessionStorage.setItem("warrior", JSON.stringify(data));
        })
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
         document.location.reload(true);

        });
    }

    async #successRequest(data) {
        if (data !== "") {
            sessionStorage.setItem("id", data.id);
            console.log("ACCOUNT");
            console.log(sessionStorage.getItem("id"));
            if (data.warrior !== 'null' ) {
                await this.#getWarrior(data.id);
            }
            await this.#showMessageToUser("Welcome back " + data.username);
       window.location = "../page/mainpageloged";
            return;
        }
        document.getElementById("loader").classList.replace("loader", "loader-invisible");
        this.#showMessageToUser("User or password wrong");
    }

    #showMessageToUser(message) {
        alert(message);
    }

    #getWarrior(accountId) {
        this.#buildRequestToGetWarrior(accountId);
        this.#requestSender.sendRequest();
    }

    #transformDataIntoJsonValue() {
        return JSON.stringify(this.#data);
    }

    setData(data) {
        this.#data = data;
    }

    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }

    setLoader(loader) {
        this.#loader = loader;
    }

}