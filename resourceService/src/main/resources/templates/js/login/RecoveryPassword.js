export class RecoveryPassword {
    #requestSender;
    #code;
    #validCode
    #username
    async recovery(username) {
        this.#username=username;
        let code;
        let newPassword;
        await this.#buildRequest(username);
        await this.#requestSender.sendRequest().then(
            () => {
                code = prompt("Insert the code");
                console.log(code);
                console.log(this.#code);
                if (this.#checkCode(code)) {
                    this.#validCode=true;
                    newPassword = this.#choseNewPassword();
                }else {
                    alert("Wrong Code");
                }
            }).then(()=>{
                if(this.#validCode){
                this.#buildRequestToChangePassword(newPassword);
                this.#requestSender.sendRequest();
                }
            }

        )

    }

    #buildRequestToChangePassword(password){
        this.#requestSender.setUrl("../recoveryPassword/changePassword");
        this.#requestSender.setRequestType("put");
        let account={
            username:this.#username,
            password:password
        }
        this.#requestSender.setJsonValue(JSON.stringify(account));
        this.#requestSender.setSuccessRequestAction((menssage) => {
            alert(menssage);
        });
        this.#requestSender.setErrorRequestAction((exp)=>{
            alert(exp);
        })
    }
    #choseNewPassword() {
        return prompt("Chose your new password")
    }

    #buildRequest(username) {
        this.#requestSender.setUrl("../recoveryPassword/" + username);
        this.#requestSender.setRequestType("get");
        this.#requestSender.setSuccessRequestAction((code) => {
            this.#code = code
        });
    }

    #checkCode(code) {
        return (this.#code === code)
    }

    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }
}