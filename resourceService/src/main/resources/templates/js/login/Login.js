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
            this.#successRequest(data);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        });
    }

    #successRequest(data) {
        if (data !== "") {
            sessionStorage.setItem("id", data.id);
            sessionStorage.setItem("warrior", JSON.stringify(data.warrior));
            this.#showMessageToUser("Welcome back " + data.username);
            window.location = "../page/mainpageloged";
            return;
        }
        document.getElementById("loader").classList.replace("loader", "loader-invisible");
        showMessageToUser("User or password wrong");
    }

    #showMessageToUser(message) {
        alert(message);
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