export class RegistryWarrior {
    #accountId;
    #data;
    #typeOfWarrior;
    #loader;
    #requestSender;

    constructor() {


    }

    async registry() {
        await this.#buildRequest();
        await this.#requestSender.sendRequest();
        await console.log(sessionStorage.getItem("warrior"));
    }

    #buildRequest() {
        this.#requestSender.setJsonValue(this.#transformDataIntoJsonValue());
        this.#requestSender.setUrl("../registrywarrior");
        this.#requestSender.setRequestType('post');
        this.#requestSender.setSuccessRequestAction((data) => {
            console.log(data);
            this.#successRequestAction(data);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            this.#errorRequestAction(status);
        })
    }




    #successRequestAction(data) {
        alert(data);
        console.log(data);
        if (data == "success") {
            console.log(data);
            sessionStorage.setItem("id",data);
            return;
        }
     //   document.location.reload(true);
    }

    #errorRequestAction(status) {
        alert(status.statusText);
        alert("Please try again later");
     //   document.location.reload(true);
    }


    #transformDataIntoJsonValue() {
        return JSON.stringify(this.#data);
    }

    setLoader(loader) {
        this.#loader = loader;
    }

    setAccountId(id) {
        this.#accountId = id;
    };

    setData(data) {
        this.#data = data;
    };

    setTypeOfWarrior(type) {
        this.#typeOfWarrior = type;
    };


    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    };
}