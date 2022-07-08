function validateFormInserisciIndirizzo(){
    let via = document.forms["inserisci-indirizzo"]["viar"].value;
    let civico = document.forms["inserisci-indirizzo"]["civicor"].value;
    let provincia = document.forms["inserisci-indirizzo"]["provinciar"].value;
    let citta = document.forms["inserisci-indirizzo"]["cittar"].value;
    let cap = document.forms["inserisci-indirizzo"]["capr"].value;
    let c = 0;
    let numerionly = /^[0-9]+$/;

    document.getElementById("viaP").innerText = "";
    document.getElementById("civicoP").innerText = "";
    document.getElementById("provinciaP").innerText = "";
    document.getElementById("cittaP").innerText = "";
    document.getElementById("capP").innerText = "";

    if (via.length > 40){
        document.getElementById("viaP").innerText = "La via non deve superare i 40 caratteri.";
        c++;
    }

    if (civico.length > 5){
        document.getElementById("civicoP").innerText = "Il numero civico ha massimo 5 cifre.";
        c++;
    }

    if (!civico.match(numerionly)){ c++;
        document.getElementById("civicoP2").innerText = "Il civico può contenere solo numeri.";
    }

    if (provincia.length > 30){
        document.getElementById("provinciaP").innerText = "La provincia non deve superare i 30 caratteri.";
        c++;
    }

    if (citta.length > 50){
        document.getElementById("cittaP").innerText = "La città non deve superare i 50 caratteri.";
        c++;
    }

    if (cap.length != 5){
        document.getElementById("capP").innerText = "Il CAP contiene 5 caratteri.";
        c++;
    }

    if (!cap.match(numerionly)){ c++;
        document.getElementById("capP2").innerText = "Il CAP può contenere solo numeri.";
    }

    if (c>0) {
        return false;
    }
    else return true;

}