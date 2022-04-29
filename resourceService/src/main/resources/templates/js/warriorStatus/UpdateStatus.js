function status(typeOfStatus) {
    let warrior = sessionStorage.getItem("warrior");
    warrior=JSON.parse(warrior);
    if (warrior.points.pointsAvailable <= 0) {
        alert("You dont have points for that");
        return;
    }

    switch (typeOfStatus) {
        case "Attack":
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
   console.log(warrior);
    // buildRequest();
    //requestSender.sendRequest();
}