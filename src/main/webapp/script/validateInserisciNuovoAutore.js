function validateInserisciNuovoAutore() {
    let nome = document.forms["gestisci-autore"]["nome"].value;

    document.getElementById("nomeP").innerText = "";

    if (nome.length > 40) {
        document.getElementById("nomeP").innerText = "Il nome dell'autore non deve superare i 40 caratteri";
        return false;
    }

    return true;

}