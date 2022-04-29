export class RequestSender {
    #successRequestAction;
    #errorRequestAction;
    #jsonValue;
    #url;
    #requestType;

    constructor() {

    }


    async sendRequest() {
        await $.ajax({
            type: this.#requestType,
            url: this.#url,
            data: this.#jsonValue,
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: (data,textStatus,request) => {
                this.#successRequestAction(data,textStatus,request);

            },
            error: (errorThrown) => {
                this.#errorRequestAction(errorThrown);

            }
        });
    }

    setSuccessRequestAction(successRequestAction) {
        this.#successRequestAction = successRequestAction
    }

    setErrorRequestAction(errorRequestAction) {
        this.#errorRequestAction = errorRequestAction;
    }

    setJsonValue(jsonValue) {
        this.#jsonValue = jsonValue;
    }

    setUrl(url) {
        this.#url = url;
    }

    setRequestType(requestType) {
        this.#requestType = requestType;
    }


}


