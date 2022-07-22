function validateFormInsLibro(){
    let isbnL = document.forms["inseriscilibro"]["isbn"].value;
    let titoloL = document.forms["inseriscilibro"]["titolo"].value;
    let altroL = document.forms["inseriscilibro"]["altro"].value;
    let descrizioneL = document.forms["inseriscilibro"]["descrizione"].value;
    let editoreL = document.forms["inseriscilibro"]["editore"].value;
    let numerionly = /^[0-9]+$/;

    let c = 0;

    const checkboxes = document.querySelectorAll('input[name="genere"]');

    var flag = controllacheckboxes(checkboxes);

    console.log(flag);

    document.getElementById("isbnP").innerText = "";
    document.getElementById("titoloP").innerText = "";
    document.getElementById("descrizioneP").innerText = "";
    document.getElementById("editoreP").innerText = "";
    document.getElementById("altroP").innerText = "";

    if (!flag) {
        document.getElementById("controllogenere").innerText = "seleziona un genere";
        c++;
    }

    if (isbnL.length != 13 || !isbnL.match(numerionly)){
            document.getElementById("isbnP").innerText = "Il codice ISBN deve essere di 13 cifre";
            c++;
    }

    if (titoloL.length > 50){
        document.getElementById("titoloP").innerText = "La lunghezza del titolo non deve superare i 50 caratteri.";
        c++;
    }

    if (altroL.length > 20){
        document.getElementById("altroP").innerText = "Il genere può avere massimo 20 caratteri.";
        c++;
    }

    if (descrizioneL.length > 65535){
        document.getElementById("descrizioneP").innerText = "La descrizione deve rientrare nei 65535 caratteri";
        c++;
    }

    if (editoreL.length > 20){
        document.getElementById("editoreP").innerText = "Il nome dell'editore non deve superare i 20 caratteri.";
        c++;
    }

    if (c>0) {
        return false;
    }
    else return true;

}

function controllacheckboxes(checkboxes) {

    for (i = 0; i < checkboxes.length; i++){
        let att = checkboxes[i].checked;

        if (att)
            return true;
    }

    return false;
}