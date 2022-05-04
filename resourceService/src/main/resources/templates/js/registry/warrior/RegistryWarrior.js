export class RegistryWarrior {
    #accountId;
    #data;
    #loader;
    #requestSender;

    constructor() {


    }

    async registry() {
        await this.#buildRequest();
        await this.#requestSender.sendRequest();

    }

    #buildRequest() {
        console.log(this.#data);
        alert("test1");
        this.#requestSender.setJsonValue(this.#transformDataIntoJsonValue());
        this.#requestSender.setUrl("../registrywarrior");
        this.#requestSender.setRequestType('post');
        this.#requestSender.setSuccessRequestAction((data) => {
            this.#successRequestAction(data);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            this.#errorRequestAction(status);
        })
    }




    #successRequestAction(data) {
        alert(data);
        console.log(data);

            sessionStorage.setItem("warrior",data);

   // document.location.reload(true);
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



    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    };
}