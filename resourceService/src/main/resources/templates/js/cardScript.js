var warrior = JSON.parse(sessionStorage.getItem("warrior"));
window.onload = () => {
    if (warrior == null) {
        window.location = "../page/createwarrior";
        return;
    }
    setUpWarriorInfo();
    updateStatus(warrior.id);
}

/*const target = {
    clicked: 0,
    currentFollowers: 90,
    btn: document.querySelector("a.btn"),
    fw: document.querySelector("span.followers")
};*/

function setUpWarriorInfo() {
    console.log(warrior);
    document.getElementById("warriorLvl").innerText = warrior.lvl;
    document.getElementById("warriorImg").src = "../resource/img/" + warrior.warriorType.toLowerCase() + ".jpeg";
    document.getElementById("warriorName").innerText = warrior.name;
    document.getElementById("warriorType").innerText = warrior.warriorType;
    document.getElementById("pointAvailable").innerText = warrior.points.pointsAvailable;

}

let percentageArray = [];
percentageArray.push(warrior.lvl);
percentageArray.push(warrior.points.damage * 2);
percentageArray.push(warrior.points.life * 2);
percentageArray.push(warrior.points.armor * 2);
percentageArray.push(warrior.points.speed * 2);

var answerArray = [];
answerArray.push('Experience');
answerArray.push('Attack:  ' + warrior.status.damage);
answerArray.push('Life:   ' + warrior.status.life);
answerArray.push('Armor:  ' + warrior.status.armor);
answerArray.push('Speed:  ' + warrior.status.speed);


//Method to create status bars
$.fn.createStatusBars = function () {
    let chartContainer = $(this);
    let barChart = $('<ul/>', {class: 'bar-chart'});
    barChart.appendTo(chartContainer);
    fieldBars(barChart);
};

function fieldBars(barChart) {
    $.each(answerArray, function (index, value) {

        let valueToGo = value.replace(/[0-9]/g, '').replace(":", "").trim();
        let chartAnswer = $('<li/>', {class: 'answer-' + index}),
            nameOfStatus = $('<span/>', {class: 'label', text: value}),
            percentageValue = percentageArray[index].toString(),
            statusPercentage = $('<span/>', {class: 'percentage', text: percentageValue.replace('.', ',') + '%'}),
            barTrack = $('<span/>', {class: 'bar-track'}),
            button = $('<button/>', {text: "+", id: "status-button", onclick: "status('" + valueToGo + "')"}),
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
}

async function status(typeOfStatus) {
    let warrior = JSON.parse(sessionStorage.getItem("warrior"));
    if (warrior.points.pointsAvailable <= 0) {
        alert("You dont have points for that");
        return;
    }
    var warriorToSend;
    switch (typeOfStatus) {
        case "Attack":
            warrior.points.damage++;
            break;
        case "Life":
            console.log("LIFE")
            warrior.points.life++;
            break;
        case "Armor":
            warrior.points.armor++;
            break;
        case "Speed":
            warrior.points.speed++;
            break;
    }
    warrior.points.pointsAvailable--;
    warriorToSend = JSON.stringify(warrior);
    console.log("Warrior to send --_>" + warriorToSend);
    await $.ajax({
        type: 'put',
        data: warriorToSend,
        url: '../warrior/updatestatus',
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
            sessionStorage.setItem("warrior", JSON.stringify(data));
            document.location.reload(true);
        },
        error: (status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        }
    })
}

$('#live-poll-area .answer-list').createStatusBars();

function logout() {
    console.log("logout");
    if (sessionStorage.getItem("id") !== "null") {
        sessionStorage.setItem("id", null);
        window.location = "../page/mainpage"
        return;
    }

}

async function updateStatus(warriorId) {
    await $.ajax({
        type: 'get',
        url: '../warrior/api/get/' + warriorId,
        traditional: true,
        success: (data) => {

            sessionStorage.setItem("warrior", JSON.stringify(data));
            warrior = data;
        },
        error: (status) => {
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        }
    })
}