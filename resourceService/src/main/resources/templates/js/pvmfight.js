
async function fight() {
  let warrior= JSON.parse(sessionStorage.getItem("warrior"));

    await $.ajax({
        type: 'post',
        url: '../play/pvmfight/'+warrior.id,
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: (data) => {
          console.log(data)
        },
        error: (status)=>{
            alert(status.statusText);
            alert("Please try again later");
            document.location.reload(true);

        }
    })
}