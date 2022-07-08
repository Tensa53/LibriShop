function validateFormModificaIndirizzo(){
    let via = document.forms["modifica-indirizzo"]["via"].value;
    let civico = document.forms["modifica-indirizzo"]["civico"].value;
    let provincia = document.forms["modifica-indirizzo"]["provincia"].value;
    let citta = document.forms["modifica-indirizzo"]["citta"].value;
    let cap = document.forms["modifica-indirizzo"]["cap"].value;
    let c = 0;
    let numerionly = /^[0-9]+$/;

    document.getElementById("viaP3").innerText = "";
    document.getElementById("civicoP3").innerText = "";
    document.getElementById("provinciaP3").innerText = "";
    document.getElementById("cittaP3").innerText = "";
    document.getElementById("capP3").innerText = "";

    if (via.length > 40){
        document.getElementById("viaP3").innerText = "La via non deve superare i 40 caratteri.";
        c++;
    }

    if (civico.length > 5){
        document.getElementById("civicoP3").innerText = "Il numero civico ha massimo 5 cifre.";
        c++;
    }

    if (!civico.match(numerionly)){ c++;
        document.getElementById("civicoP4").innerText = "Il civico può contenere solo numeri.";
    }

    if (provincia.length > 30){
        document.getElementById("provinciaP3").innerText = "La provincia non deve superare i 30 caratteri.";
        c++;
    }

    if (citta.length > 50){
        document.getElementById("cittaP3").innerText = "La città non deve superare i 50 caratteri.";
        c++;
    }

    if (cap.length != 5){
        document.getElementById("capP3").innerText = "Il CAP contiene 5 caratteri.";
        c++;
    }

    if (!cap.match(numerionly)){ c++;
        document.getElementById("capP4").innerText = "Il CAP può contenere solo numeri.";
    }

    if (c>0) {
        return false;
    }
    else return true;

}