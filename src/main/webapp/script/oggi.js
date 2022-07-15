function oggi () {
    let dataP = document.getElementById("dataPubblicazione");

    const date = new Date();

    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    let currentDate = `${day}-${month}-${year}`;
    console.log(currentDate);

    dataP.setAttribute("max",currentDate);


}

window.onload = function () {
    oggi();
}