

export class WarriorStatusCard {
    #warriorStatusElements;
    #warrior;
    #requestSender;

    buildStatusBars() {
        this.#warriorStatusElements.setWarrior(this.#warrior);
        this.#warriorStatusElements.buildInfoWithPointsAvailable();
        this.#warriorStatusElements.buildPercentageBars();
        this.#warriorStatusElements.buildValueArray();
        $.fn.createStatusBars = function (valueArray,percentageArray) {
            let chartContainer = $(this);
            let barChart = $('<ul/>', {class: 'bar-chart'});
            barChart.appendTo(chartContainer);


            $.each(valueArray, function (index, value) {
                let statusName = value.replace(/[0-9]/g, '').replace(":", "").trim();
                let chartAnswer = $('<li/>', {class: 'answer-' + index}),
                    nameOfStatus = $('<span/>', {class: 'label', text: value}),
                    percentageValue = percentageArray[index].toString(),
                    statusPercentage = $('<span/>', {
                        class: 'percentage',
                        text: percentageValue.replace('.', ',') + '%'
                    }),
                    barTrack = $('<span/>', {class: 'bar-track'}),
                    button = $('<button/>', {type:"button", text: "+", id: statusName}),
                    bar = $('<span />', {class: 'bar', style: 'width: ' + percentageValue + '%;'});
                chartAnswer.appendTo(barChart);
                nameOfStatus.appendTo(chartAnswer);
                statusPercentage.appendTo(chartAnswer);
                if (value != "Experience") {
                    button.appendTo(statusPercentage);
                }
                barTrack.appendTo(chartAnswer);
                bar.appendTo(barTrack);


            });
        };

        $('#live-poll-area .answer-list').createStatusBars(this.#warriorStatusElements.getValueArray(),this.#warriorStatusElements.getPercentageArray());
        this.#setUpButtonEvents();

    }

    buildStatusBarsWithoutPlusButton() {
        console.log("Build inside satus bars");
        console.log(this.#warrior);
        let allElements = document.getElementsByClassName("bar-chart");
        let barchart = document.getElementsByClassName("bar-chart")[(allElements.length) - 1];
        if (!(barchart === undefined)) {
            this.removeAllElements(allElements);
        }
        this.#warriorStatusElements.setWarrior(this.#warrior);
        this.#warriorStatusElements.buildInfo();
        this.#warriorStatusElements.buildPercentageBars();
        this.#warriorStatusElements.buildValueArray();

        $.fn.createStatusBars = function (valueArray,percentageArray) {
            console.log("VALUE ARRAY");
            console.log(valueArray);
            let chartContainer = $(this);
            let barChart = $('<ul/>', {class: 'bar-chart'});
            barChart.appendTo(chartContainer);


            $.each(valueArray, function (index, value) {
                let statusName = value.replace(/[0-9]/g, '').replace(":", "").trim();
                let chartAnswer = $('<li/>', {class: 'answer-' + index}),
                    nameOfStatus = $('<span/>', {class: 'label', text: value}),
                    percentageValue = percentageArray[index].toString(),
                    statusPercentage = $('<span/>', {
                        class: 'percentage',
                        text: percentageValue.replace('.', ',') + '%'
                    }),
                    barTrack = $('<span/>', {class: 'bar-track'}),
                    bar = $('<span />', {class: 'bar', style: 'width: ' + percentageValue + '%;'});
                chartAnswer.appendTo(barChart);
                nameOfStatus.appendTo(chartAnswer);
                statusPercentage.appendTo(chartAnswer);
                barTrack.appendTo(chartAnswer);
                bar.appendTo(barTrack);


            });
        };

        $('#live-poll-area .answer-list').createStatusBars(this.#warriorStatusElements.getValueArray(),this.#warriorStatusElements.getPercentageArray());

    }

    #setUpButtonEvents(){
        $('#Attack').click(()=>{
            this.#status('Attack');
        });
        $('#Life').click(()=>{
            this.#status('Life');
        });
        $('#Armor').click(()=>{
            this.#status('Armor');
        });
        $('#Speed').click(()=>{
            this.#status('Speed');
        });
    }
    removeAllElements(allElements) {
        for (let i = 1; i < allElements.length; i++) {
            allElements[i].innerHTML = "";
        }
    }
    async #status(typeOfStatus) {
        if (this.#warrior.points.pointsAvailable <= 0) {
            alert("You dont have points for that");
            return;
        }

        switch (typeOfStatus) {
            case "Attack":
                this.#warrior.points.damage++;
                break;
            case "Life":
                this.#warrior.points.life++;
                break;
            case "Armor":
                this.#warrior.points.armor++;
                break;
            case "Speed":
                this.#warrior.points.speed++;
                break;
        }
        this.#warrior.points.pointsAvailable--;
        this.#buildRequest();
        this.#requestSender.sendRequest();
    }

    #buildRequest() {
        this.#requestSender.setJsonValue(JSON.stringify(this.#warrior));
        this.#requestSender.setRequestType("put");
        this.#requestSender.setUrl("../warrior/updatestatus");
        this.#requestSender.setSuccessRequestAction((data) => {
            sessionStorage.setItem("warrior", JSON.stringify(data));
            document.location.reload(true);
        });
        this.#requestSender.setErrorRequestAction((status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        });
    }

    setRequestSender(requestSender) {
        this.#requestSender = requestSender;
    }

    setWarrior(warrior) {
        this.#warrior = warrior;
    }

    setWarriorStatusElements(warriorStatusElements) {
        this.#warriorStatusElements = warriorStatusElements;
    }
}










































