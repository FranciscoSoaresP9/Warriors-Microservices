export class WarriorStatusElements {
    #lvlElement;
    #imgElement;
    #nameElement;
    #typeElement;
    #pointsAvailableElement;
    #percentageArray = [];
    #valueArray = [];
    constructor(warrior) {
        if (!warrior) {
            console.log("Need pass a warrior");
            return;
        }
        this.#lvlElement = document.getElementById("warriorLvl");
        this.#imgElement = document.getElementById("warriorImg");
        this.#nameElement = document.getElementById("warriorName");
        this.#typeElement = document.getElementById("warriorType");
        this.#pointsAvailableElement = document.getElementById("pointAvailable");
        this.buildInfo(warrior);
        this.buildPercentageBars(warrior);
        this.buildValueArray(warrior);
    }

    buildInfo(warrior) {
        console.log(warrior);
        this.#lvlElement.innerText = warrior.lvl;
        this.#imgElement.src = "../resource/img/" + warrior.warriorType.toLowerCase() + ".jpeg";
        this.#nameElement.innerText = warrior.name;
        this.#typeElement.innerText = warrior.warriorType;
        this.#pointsAvailableElement.innerText = warrior.points.pointsAvailable;
    }

    buildPercentageBars(warrior) {

        this.#percentageArray.push(warrior.lvl);
        this.#percentageArray.push(warrior.points.damage * 2);
        this.#percentageArray.push(warrior.points.life * 2);
        this.#percentageArray.push(warrior.points.armor * 2);
        this.#percentageArray.push(warrior.points.speed * 2);
    }

    buildValueArray(warrior) {
        this.#valueArray.push('Attack:  ' + warrior.status.damage);
        this.#valueArray.push('Life:   ' + warrior.status.life);
        this.#valueArray.push('Armor:  ' + warrior.status.armor);
        this.#valueArray.push('Speed:  ' + warrior.status.speed);
    }

    getPercentageArray(){
        return this.#percentageArray;
    }

    getValueArray(){
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

}