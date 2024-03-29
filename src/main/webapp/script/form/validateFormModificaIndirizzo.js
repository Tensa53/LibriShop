function validateFormModificaIndirizzo(){
    let via = document.forms["modifica-indirizzo"]["via"].value;
    let civico = document.forms["modifica-indirizzo"]["civico"].value;
    let cap = document.forms["modifica-indirizzo"]["cap"].value;
    let c = 0;
    let numerionly = /^[0-9]+$/;

    document.getElementById("viaP").innerText = "";
    document.getElementById("civicoP").innerText = "";
    document.getElementById("capP").innerText = "";

    if (via.length > 40){
        document.getElementById("viaP").innerText = "La via non deve superare i 40 caratteri.";
        c++;
    }

    if (civico.match(numerionly)) {
        if (civico.length > 5){
            document.getElementById("civicoP").innerText = "Il numero civico ha massimo 5 cifre.";
            c++;
        }
    } else {
        document.getElementById("civicoP").innerText = "Il numero civico deve essere formato solamente da cifre.";
    }

    if (cap.match(numerionly)) {
        if (cap.length != 5){
            document.getElementById("capP").innerText = "Il CAP deve essere di 5 cifre.";
            c++;
        }
    } else {
        document.getElementById("capP").innerText = "Il CAP deve essere formato solamente da cifre.";
    }

    if (c>0) {
        return false;
    }
    else return true;

}