const warrior = JSON.parse(sessionStorage.getItem("warrior"));
const serverUrl = "http://localhost:8088";
window.onload = () => {
    if(warrior==null){
        window.location = serverUrl + "/page/createwarrior";
        return;
    }
setUpWarriorInfo();

}

const target = {
    clicked: 0,
    currentFollowers: 90,
    btn: document.querySelector("a.btn"),
    fw: document.querySelector("span.followers")
};
function setUpWarriorInfo() {
    console.log(warrior);
    document.getElementById("warriorLvl").innerText=warrior.lvl;
    document.getElementById("warriorImg").src="../resource/img/"+warrior.warriorType.toLowerCase()+".jpeg";
    document.getElementById("warriorName").innerText = warrior.name;
    document.getElementById("warriorType").innerText = warrior.warriorType;
    document.getElementById("pointAvailable").innerText=warrior.points.pointsAvailable;

}
let percentageArray = new Array();
percentageArray.push(warrior.lvl);
percentageArray.push(warrior.points.damage*2);
percentageArray.push(warrior.points.life*2);
percentageArray.push(warrior.points.armor*2);
percentageArray.push(warrior.points.speed*2);

var answerArray = new Array();
answerArray.push('Experience');
answerArray.push('Attack:  '+warrior.status.damage);
answerArray.push('Life:   '+warrior.status.life);
answerArray.push('Armor:  '+warrior.status.armor);
answerArray.push('Speed:  '+warrior.status.speed);

$.fn.createBarchart = function (optionvariables) {
    var chartContainer = $(this);
    var defaults = {
        'maxWidth': 244
    };
    var options = $.extend({}, defaults, optionvariables);
    var self = $(this),
        graphContainer = self.parent().find('.graph-container .graph'),
        barChart = $('<ul/>', {class: 'bar-chart'});

    barChart.appendTo(chartContainer);

    $.each(answerArray, function (index, value) {
       var withoutSpaces=value.trim();
       var withoutNumbers= withoutSpaces.replace(/[0-9]/g, '');
       var valueToGo = withoutNumbers.replace(":","");
        var chartAnswer = $('<li/>', {class: 'answer-' + index}),
            answerLabel = $('<span/>', {class: 'label', text: value}),
            percentageValue = percentageArray[index].toString(),
            answerPercentage = $('<span/>', {class: 'percentage', text: percentageValue.replace('.', ',') + '%'}),
            barTrack = $('<span/>', {class: 'bar-track'}),
            button=$('<button/>',{text:"+",id:"status-button",onclick:"status('"+valueToGo+"')"}),
            bar = $('<span />', {class: 'bar', style: 'width: ' + percentageValue + '%;'});

        chartAnswer.appendTo(barChart);
        answerLabel.appendTo(chartAnswer);
        answerPercentage.appendTo(chartAnswer);
        if(value!="Experience"){
            button.appendTo(answerPercentage);
        }
        barTrack.appendTo(chartAnswer);
        bar.appendTo(barTrack);
    });

    if ($('html').hasClass('canvas')) {
        barChart.chart(
            {
                graphContainer: graphContainer
            }
        );
    }
};

$.fn.chart = function (optionvariables) {
    var chart = $(this);
    var defaults = {
        'canvasSize': 220,
        'graphContainer': $('.graph-container .graph')
    };
    var options = $.extend({}, defaults, optionvariables);

    return chart.each(function () {
        var listItem = chart.find('li'),
            listItems = listItem.length,
            canvas = document.createElement('canvas'),
            canvasWidth = options.canvasSize,
            canvasHeight = options.canvasSize,
            graphContainer = options.graphContainer,
            total = 0,
            totalPercentage = 0,
            data = [],
            newData = [],
            i = 0,
            startingAngle,
            arcSize,
            endingAngle;

        $.each(percentageArray, function (index, value) {
            newData.push(3.6 * value);
        });

        function sumTo(a, i) {
            var sum = 0;
            for (var j = 0; j < i; j++) {
                sum += a[j];
            }
            return sum - 90;
        }

        function degreesToRadians(degrees) {
            return ((degrees * Math.PI) / 180);
        }

        canvas.setAttribute('width', canvasWidth);
        canvas.setAttribute('height', canvasHeight);
        canvas.setAttribute('id', 'chartCanvas');
        graphContainer.append(canvas);

        var cvs = document.getElementById('chartCanvas'),
            ctx = cvs.getContext('2d'),
            centerX = canvasWidth / 2,
            centerY = canvasHeight / 2,
            radius = canvasWidth / 2;

        ctx.clearRect(0, 0, canvasWidth, canvasHeight);

        listItem.each(function (e) {
            startingAngle = degreesToRadians(sumTo(newData, i));
            arcSize = degreesToRadians(newData[i]);
            endingAngle = startingAngle + arcSize;
            ctx.beginPath();
            ctx.moveTo(centerX, centerY);
            ctx.arc(centerX, centerY, radius, startingAngle, endingAngle, false);
            ctx.closePath();
            ctx.fillStyle = $(this).find('.bar').css('backgroundColor');
            ctx.fill();
            ctx.restore();
            i++;
        });

        ctx.beginPath();
        ctx.moveTo(centerX, centerY);
        ctx.arc(centerX, centerY, radius * .45, 0, 2 * Math.PI, false);
        ctx.closePath();
        ctx.fillStyle = $('body').css('backgroundColor');
        ctx.fill();
    });
};

async function  status(typeOfStatus) {

    let warrior= JSON.parse(sessionStorage.getItem("warrior"));
    if(warrior.points.pointsAvailable<=0){
        alert("You dont have points for that");
        return;
    }
    var warriorToSend;
    console.log(typeOfStatus+"-")
    console.log(typeOfStatus==="Attack")
    switch (typeOfStatus){
        case "Attack":
            console.log("where")
            warrior.points.damage++;
            break;
        case "Life":
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
     warriorToSend= JSON.stringify(warrior);
     console.log(warriorToSend);
    await $.ajax({
        type: 'post',
        data:warriorToSend,
        url: serverUrl + '/warrior/updatestatus',
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
            sessionStorage.setItem("warrior",JSON.stringify(data));
         //   document.location.reload(true);
        }
    })
}


$('#live-poll-area .answer-list').createBarchart();
