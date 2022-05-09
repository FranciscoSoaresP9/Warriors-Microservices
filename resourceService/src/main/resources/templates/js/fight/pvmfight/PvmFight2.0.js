export class PvmFight20 {
    #warrior;
    #requestSender;
    #monster
    #MonsterStatusCard;
    #warriorStatusCard

    constructor() {
        $('.fightButton').click(() => {
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
            this.#successRequestAction(data);
        })
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        })

    }

    #successRequestAction(data) {
        console.log("WARRIOR");
        console.log(data.warrior);
        this.#warriorStatusCard.setWarrior(data.warrior);
        this.#warriorStatusCard.buildStatusBarsWithoutPlusButton();

        this.#MonsterStatusCard.setMonster(data.gameElementAttacked);
        this.#MonsterStatusCard.buildStatusBarsWithoutPlusButton();
    }

    setWarrior(warrior) {
        this.#warrior = warrior;
    }

    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }

    setMonsterStatusCard(monsterStatusCard) {
        this.#MonsterStatusCard = monsterStatusCard;
    }

    setWarriorStatusCard(warriorStatusCard) {
        this.#warriorStatusCard = warriorStatusCard
    }
}