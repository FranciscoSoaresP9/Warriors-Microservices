class requestSender {
    constructor() {
    }

    async requestSender(jsonValue) {
        await $.ajax({
            type: 'post',
            url: '../login',
            data: jsonValue,
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: (data) => {
                displayMessageToUser(data);

            },
            error: (status) => {
                alert(status.statusText);
                alert("Please try again later");
                document.location.reload(true);

            }
        });
    }
}

