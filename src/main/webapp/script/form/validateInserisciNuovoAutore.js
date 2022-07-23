function validateInserisciNuovoAutore() {
    let c = 0;

    let nome = document.forms["gestisci-autore"]["nome"].value;
    let cf = document.forms["gestici-autore"]["CF"].value;
    let cfformat = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/i;

    document.getElementById("nomeP").innerText = "";
    document.getElementById("CFP").innerText = "";

    if (nome.length > 40) {
        document.getElementById("nomeP").innerText = "Il nome dell'autore non deve superare i 40 caratteri.";
        c++;
    }

    if(!cf.match(cfformat)) {
        if (cf.length != 16) {
            document.getElementById("CFP").innerText = "Il CF dell'autore deve essere di 16 cifre.";
            c++;
        }
    } else {
        document.getElementById("CFP").innerText = "Inserisci un CF dal formato valido."
    }


    if(c > 0) {
        return false;
    }
    else
        return true;

}