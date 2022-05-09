export class MonsterStatusCard {
    #monsterStatusElements;
    #monster;


    buildStatusBarsWithoutPlusButton() {
        let allElements = document.getElementsByClassName("bar-chart");
        let barchart = document.getElementsByClassName("bar-chart")[(allElements.length) - 1];
        if (!(barchart === undefined)) {
            console.log("removed monster");
            this.removeAllElements(allElements);
        }

        this.#monsterStatusElements.setMonster(this.#monster);
        this.#monsterStatusElements.buildInfo();
        this.#monsterStatusElements.buildPercentageBars();
        this.#monsterStatusElements.buildValueArray();

        $.fn.createStatusBars = function (valueArray, percentageArray) {
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

        $('#live-poll-area-monster .answer-list').createStatusBars(this.#monsterStatusElements.getValueArray(), this.#monsterStatusElements.getPercentageArray());

    }


    setMonster(monster) {
        this.#monster = monster;
    }

    setMonsterStatusElements(warriorStatusElements) {
        this.#monsterStatusElements = warriorStatusElements;
    }

    removeAllElements(allElements) {
        for (let i = 1; i < allElements.length; i++) {
            allElements[i].innerHTML = "";
        }
    }


}