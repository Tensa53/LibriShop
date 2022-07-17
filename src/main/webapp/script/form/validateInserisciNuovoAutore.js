function validateInserisciNuovoAutore() {
    let c = 0;

    let nome = document.forms["gestisci-autore"]["nome"].value;
    let cf = document.forms["gestici-autore"]["CF"].value;

    document.getElementById("nomeP").innerText = "";
    document.getElementById("CFP").innerText = "";

    if (nome.length > 40) {
        document.getElementById("nomeP").innerText = "Il nome dell'autore non deve superare i 40 caratteri";
        c++;
    }

    if (cf.length != 16) {
        document.getElementById("CFP").innerText = "Il CF dell'autore deve essere di 16 cifre";
        c++;
    }

    if(c > 0) {
        return false;
    }
    else
        return true;

}