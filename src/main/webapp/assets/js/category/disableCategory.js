
function disableCategory(id, element) {
    const url = "/changeStatus?id=" + id;
    const request = new XMLHttpRequest();
    request.open("POST", url);

    request.onload = function (){
        const active = element.closest("tr").querySelector(".active");
        active.innerHTML = active.innerHTML === "true" ? "false" : "true";
    }

    request.send();
}