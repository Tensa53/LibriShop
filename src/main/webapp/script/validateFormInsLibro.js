function validateFormInsLibro(){
    let isbnL = document.forms["inseriscilibro"]["isbn"].value;
    let titoloL = document.forms["inseriscilibro"]["titolo"].value;
    let autoreL = document.forms["inseriscilibro"]["autore"].value;
    let altroL = document.forms["inseriscilibro"]["altro"].value;
    let descrizioneL = document.forms["inseriscilibro"]["descrizione"].value;
    let editoreL = document.forms["inseriscilibro"]["editore"].value;
    let c = 0;

    document.getElementById("isbnP").innerText = "";
    document.getElementById("titoloP").innerText = "";
    document.getElementById("autoreP").innerText = "";
    document.getElementById("descrizioneP").innerText = "";
    document.getElementById("editoreP").innerText = "";

    if (isbnL.length != 13){
            document.getElementById("isbnP").innerText = "Il codice ISBN deve essere di 13 cifre";
            c++;
    }

    if (titoloL.length > 30){
        document.getElementById("titoloP").innerText = "La lunghezza del titolo non deve superare le 30 cifre.";
        c++;
    }

    if (autoreL.length > 40){
        document.getElementById("autoreP").innerText = "La lunghezza del nome dell'autore non deve superare le 40 cifre.";
        c++;
    }

    if (altroL.length > 20){
        document.getElementById("altroP").innerText = "Il genere può avere massimo 20 caratteri.";
        c++;
    }

    if (descrizioneL.length > 500){
        document.getElementById("descrizioneP").innerText = "La descrizione deve rientrare il 500 caratteri.";
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