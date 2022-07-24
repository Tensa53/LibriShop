function validateFormInserisciIndirizzo(){
    let via = document.forms["inserisci-indirizzo"]["viar"].value;
    let civico = document.forms["inserisci-indirizzo"]["civicor"].value;
    let cap = document.forms["inserisci-indirizzo"]["capr"].value;
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