//import {AccountLodgedChecker} from "./AccountLodgedChecker";

window.onload = (event) => {
    let modal = document.getElementById('ticketModal');
   // const accountLodgedChecker = new AccountLodgedChecker();
   // accountLodgedChecker.check('../page/mainpageloged');
    if (event.target == modal) {
        modal.style.display = "none";
    }

}
// Automatic Slideshow - change image every 4 seconds


var myIndex = 0;
function carousel() {

    let i;
    let x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) {
        myIndex = 1
    }
    x[myIndex - 1].style.display = "block";
    console.log("TEST")
    setTimeout(carousel, 4000);
}

// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
    let x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}
carousel();

