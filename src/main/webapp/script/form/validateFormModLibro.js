function validateFormModLibro() {
    let titoloL = document.forms["modificalibro"]["titolo"].value;
    let altroL = document.forms["modificalibro"]["altro"].value;
    let descrizioneL = document.forms["modificalibro"]["descrizione"].value;
    let editoreL = document.forms["modificalibro"]["editore"].value;
    let prezzoL = document.forms["modificalibro"]["prezzo"].value;
    let disponibilitaL = document.forms["modificalibro"]["disponibilita"].value;
    let scontoL = document.forms["modificalibro"]["sconto"].value;

    let prezzionly = /(\d+\.\d{1,2})/;

    let c = 0;

    const checkboxes = document.querySelectorAll('input[name="genere"]');

    var flag = controllacheckboxes(checkboxes);

    console.log(flag);

    document.getElementById("titoloP").innerText = "";
    document.getElementById("descrizioneP").innerText = "";
    document.getElementById("editoreP").innerText = "";
    document.getElementById("altroP").innerText = "";

    if (!flag) {
        document.getElementById("controllogenere").innerText = "seleziona un genere";
        c++;
    }

    if (titoloL.length > 50) {
        document.getElementById("titoloP").innerText = "La lunghezza del titolo non deve superare i 50 caratteri.";
        c++;
    }

    if (altroL.length > 40) {
        document.getElementById("altroP").innerText = "Il genere può avere massimo 40 caratteri.";
        c++;
    }

    if (descrizioneL.length > 65535) {
        document.getElementById("descrizioneP").innerText = "La descrizione deve rientrare nei 65535 caratteri";
        c++;
    }

    if (editoreL.length > 20) {
        document.getElementById("editoreP").innerText = "Il nome dell'editore non deve superare i 20 caratteri.";
        c++;
    }

    if (!prezzoL.match(prezzionly)) {
        document.getElementById("prezzoP").innerText = "Il prezzo deve essere un valore numerico del tipo (22.40).";
        c++;
    }

    if (!disponibilitaL.match(numerionly)) {
        document.getElementById("disponibilitaP").innerText = "La disponibilità deve essere formata solamente da cifre.";
        c++;
    }

    if (!scontoL.match(numerionly)) {
        document.getElementById("scontoP").innerText = "Lo sconto deve essere formato solamente da cifre.";
        c++;
    }

    if (c > 0) {
        return false;
    } else return true;

}

function controllacheckboxes(checkboxes) {
    for (i = 0; i < checkboxes.length; i++){
        let att = checkboxes[i].checked;

        if (att)
            return true;
    }

    return false;
}