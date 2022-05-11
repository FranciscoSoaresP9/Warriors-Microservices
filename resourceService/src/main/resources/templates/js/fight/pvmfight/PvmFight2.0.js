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
        this.#warriorStatusCard.setWarrior(data.warrior);

        sessionStorage.setItem("warrior",JSON.stringify(data.warrior));
        this.#warriorStatusCard.buildStatusBarsWithoutPlusButton();
        this.#showResultOfBattle(data.win);
        this.#MonsterStatusCard.setMonster(data.gameElementAttacked);
        this.#MonsterStatusCard.buildStatusBarsWithoutPlusButton();
    }

    #showResultOfBattle(win) {
        if (win) {
            document.querySelector(".resultLabel").textContent = "YOU WIN";
            return;
        }
        document.querySelector(".resultLabel").textContent = "YOU LOSE";
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