export class RegistryAccount {
    #data;
    #loader;
    #requestSender;

    constructor() {
    }

    async registry() {
        this.#loader.show();
        if (!this.#checkPasswords()) {
            alert("The password and confirm password are different.")
            this.#loader.hide();
            return;
        }
        await this.#buildRequest();
        await this.#requestSender.sendRequest();
    }

    #checkPasswords() {
        return (this.#data.password === this.#data.confirm_password);
    }

    #buildRequest() {
        this.#requestSender.setJsonValue(this.#transformDataIntoJsonValue());
        this.#requestSender.setUrl("../registryaccount");
        this.#requestSender.setRequestType('post');
        this.#requestSender.setSuccessRequestAction((data, textStatus, request) => {
            this.#successRequestAction(data, textStatus, request);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            this.#errorRequestAction(status);
        })
    }

    #successRequestAction(data, textStatus, request) {
        const status = request.statusText;
        //Status code to ALREADY_REPORTED
        if (status === "Success (208)") {
            alert("This username already Taken");
            this.#loader.hide();
            return;
        }
        //Status code to IM_USE
        if (status === "Success (226)") {
            alert("This email already Taken");
            this.#loader.hide();
            return;
        }
        if (status === "Created") {
            alert("Account Created");
            window.location = "../page/login";
            return;
        }
        alert("Server Error");
        alert("Pls try again later");
        document.location.reload(true);

    }

    #errorRequestAction(status) {

        alert("Pls try again later")
        document.location.reload(true);
    }

    #transformDataIntoJsonValue() {

        delete this.#data.confirm_password;
        return JSON.stringify(this.#data);
    }


    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }

    setLoader(loader) {
        this.#loader = loader;
    }

    setData(data) {
        this.#data = data;
    }
}