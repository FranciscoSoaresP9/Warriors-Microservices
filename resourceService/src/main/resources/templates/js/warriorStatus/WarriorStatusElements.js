export class WarriorStatusElements {
    #lvlElement;
    #imgElement;
    #nameElement;
    #typeElement;
    #pointsAvailableElement;
    #percentageArray = [];
    #valueArray = [];
    #warrior;

    constructor() {

        this.#lvlElement = document.getElementById("warriorLvl");
        this.#imgElement = document.getElementById("warriorImg");
        this.#nameElement = document.getElementById("warriorName");
        this.#typeElement = document.getElementById("warriorType");
        this.#pointsAvailableElement = document.getElementById("pointAvailable");

    }

    buildInfo() {
        this.#lvlElement.innerText = this.#warrior.lvl;
        this.#imgElement.src = "../resource/img/" + this.#warrior.warriorType.toLowerCase() + ".jpeg";
        this.#nameElement.innerText = this.#warrior.name;
        this.#typeElement.innerText = this.#warrior.warriorType;
    }

    buildInfoWithPointsAvailable() {
        this.buildInfo()
        this.#pointsAvailableElement.innerText = this.#warrior.points.pointsAvailable;
    }

    buildPercentageBars() {

        this.#percentageArray=[];
        this.#percentageArray.push(this.#warrior.lvl);
        this.#percentageArray.push(this.#warrior.points.damage * 2);
        this.#percentageArray.push(this.#warrior.points.life * 2);
        this.#percentageArray.push(this.#warrior.points.armor * 2);
        this.#percentageArray.push(this.#warrior.points.speed * 2);
        console.log( this.#percentageArray)
    }

    buildValueArray() {
        this.#valueArray=[];
        this.#valueArray.push('Attack:  ' + this.#warrior.status.damage);
        this.#valueArray.push('Life:   ' + this.#warrior.status.life);
        this.#valueArray.push('Armor:  ' + this.#warrior.status.armor);
        this.#valueArray.push('Speed:  ' + this.#warrior.status.speed);
        console.log(this.#valueArray);
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

    setWarrior(warrior) {
        this.#warrior = warrior;
    }
}