export class PvmFight20 {
    #warrior;
    #requestSender;

    constructor() {
        $('.fightButton').click(()=>{
            this.fight();
        });
    }
    async fight() {
        await this.#buildRequest();
        await this.#requestSender.sendRequest();

    }

    async #buildRequest() {
        this.#requestSender.setRequestType('post');
        this.#requestSender.setUrl('../play/pvmfight/' + this.#warrior.id)
        this.#requestSender.setSuccessRequestAction((data) => {
            console.log(data)
        })
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        })

    }

    setWarrior(warrior) {
        this.#warrior = warrior;
    }

    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }
}