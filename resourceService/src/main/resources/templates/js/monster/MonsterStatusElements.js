export class MonsterStatusElements {
    #lvlElement;
    #monster
    #imgElement;
    #nameElement;
    #typeElement;
    #pointsAvailableElement;
    #percentageArray = [];
    #valueArray = [];

    constructor() {

        console.log("Constructor")
        this.#lvlElement = document.getElementById("monsterLvl");
        this.#imgElement = document.getElementById("monsterImg");
        this.#nameElement = document.getElementById("monsterName");
        this.#typeElement = document.getElementById("monsterType");

    }

    buildInfo() {
        console.log(this.#monster)
        this.#lvlElement.innerText = this.#monster.lvl;
        this.#imgElement.src = "../resource/img/monster.jpeg";
        this.#nameElement.innerText = "MONSTER"
        this.#typeElement.innerText = this.#monster.type;
    }

    buildPercentageBars() {
        this.#percentageArray = [];
        this.#percentageArray.push(this.#monster.lvl);
        this.#percentageArray.push(this.#monster.status.damage * 2);
        this.#percentageArray.push(this.#monster.status.life * 2);
        this.#percentageArray.push(this.#monster.status.armor * 2);
        this.#percentageArray.push(this.#monster.status.speed * 2);
    }

    buildValueArray() {
        this.#valueArray = [];
        this.#valueArray.push('Attack:  ' + this.#monster.status.damage);
        this.#valueArray.push('Life:   ' + this.#monster.status.life);
        this.#valueArray.push('Armor:  ' + this.#monster.status.armor);
        this.#valueArray.push('Speed:  ' + this.#monster.status.speed);
    }

    getPercentageArray() {
        return this.#percentageArray;
    }

    getValueArray() {
        return this.#valueArray;
    }

    getLvlElement() {
        return this.#lvlElement;
    }

    getImgElement() {
        return this.#imgElement;
    }

    getNameElement() {
        return this.#nameElement;
    }

    getTypeElement() {
        return this.#typeElement;
    }

    getPointsAvailableElement() {
        return this.#pointsAvailableElement;
    }

    setMonster(monster) {
        this.#monster = monster;
    }
}