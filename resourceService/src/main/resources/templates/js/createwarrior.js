var serverUrl = "http://192.168.1.108:8088";
window.onload = () => {
    console.log(sessionStorage.getItem("warrior"));
    console.log(sessionStorage.getItem("id"));
    if (sessionStorage.getItem("id") === "null") {
        if(sessionStorage.getItem("id") === null){
            window.location = serverUrl + "/mainpage";
        }
    }
    if(sessionStorage.getItem("warrior")!=="null"){
        window.location = serverUrl + "/page/warriorstatus";
    }
}


function active(element) {
    let allElements = document.getElementsByClassName("warrior-class-active")
    Object.entries(allElements).forEach(
        (value) => {
            value[1].classList.replace("warrior-class-active", "warrior-class")
        }
    )
    element.classList.replace("warrior-class", "warrior-class-active");
}

async function handleSubmit(event) {
    if(sessionStorage.getItem("warrior")==null){
        alert("Warrior allready created");
        return;
    }
    event.preventDefault();

    const data = new FormData(event.target);

    const value = Object.fromEntries(data.entries());

    const allElements = document.getElementsByClassName("warrior-class-active")

    const typeOfWarrior = allElements[0].innerText;

    const accountId = sessionStorage.getItem("id");

    value.warriorType = typeOfWarrior;
    value.accountId = accountId;

    const jsonValue = JSON.stringify(value);



    const submitButton = document.getElementById('submitbutton');
    submitButton.disabled = true;

    await $.ajax({
        type: 'post',
        url: serverUrl + '/warrior/createwarrior',
        data: jsonValue,
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
            alert(data);
            if(data=="Warrior created"){
                requestWarrior(sessionStorage.getItem("id"));
                return;
            }
         //   document.location.reload(true);

        }
    })


}

function requestWarrior(accountId) {
    console.log(accountId);
   $.ajax({
        type: 'get',
        url: serverUrl + '/account/getwarrior/'+accountId,
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
            if (data !== "") {

                sessionStorage.setItem("warrior",JSON.stringify(data));
                window.location = serverUrl + "/page/warriorstatus";
                return;
            }
        }
    })
}

const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);