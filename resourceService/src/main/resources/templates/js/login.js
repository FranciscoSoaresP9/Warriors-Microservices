window.onload = () => {
    console.log(sessionStorage.getItem("warrior"));
    console.log(sessionStorage.getItem("id"));
    if(sessionStorage.getItem("id") !== "null"){
        if(sessionStorage.getItem("id") !== null){
            window.location =  "../page/mainpageloged";
        }
    }

}

async function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);

    const value = Object.fromEntries(data.entries());

    const jsonValue = JSON.stringify(value);
    document.getElementById("loader").classList.replace("loader-invisible", "loader");
    await $.ajax({
        type: 'post',
        url:  '../login',
        data: jsonValue,
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
            console.log(data);
            if (data !== "") {
                sessionStorage.setItem("id", data.id);
                sessionStorage.setItem("warrior",JSON.stringify(data.warrior));
                alert("Welcome back " + data.username);
                window.location =  "../page/mainpageloged";

                return;
            }
            document.getElementById("loader").classList.replace("loader", "loader-invisible");
            alert("User or password wrong");
        },
        error: (status)=>{
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        }
    });


}

const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);