function active(element) {
    let allElements = document.getElementsByClassName("warrior-class-active")
    Object.entries(allElements).forEach(
        (value) => {
            value[1].classList.replace("warrior-class-active", "warrior-class")
        }
    )
    element.classList.replace("warrior-class", "warrior-class-active");
}