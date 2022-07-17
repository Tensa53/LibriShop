function validateFormInserisciPagamento(){
    let carta = document.forms["inserisci-pagamento"]["numeroCarta"].value;
    let ccv = document.forms["inserisci-pagamento"]["ccv"].value;

    let c = 0;

    document.getElementById("cartaP").innerText = "";
    document.getElementById("ccvP").innerText = "";

    if (carta.length > 16) {
        document.getElementById("cartaP").innerText = "Il numero carta ha massimo 16 cifre.";
        c++;
    }

    if (ccv.length != 3) {
        document.getElementById("ccvP").innerText = "Il ccv contiene 3 cifre.";
        c++;
    }

    if (c > 0)
        return false;
    else
        return true;

}