function dataMin() {
    const d = new Date();

    const year = d.getFullYear();

    var month = d.getMonth() + 1;

    var day = d.getDate();

    if (month < 10)
        month = '0' + month;

    if (day < 10)
        day = '0' + day;

    const dataString = year + '-' + month + '-' + day;

    console.log(dataString);

    var scadenza = document.getElementById('scadenza');

    console.log(scadenza);

    scadenza.setAttribute('min',dataString);
}

window.onload = dataMin();