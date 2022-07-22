function validateFormInserisciPagamento(){
    let carta = document.forms["inserisci-pagamento"]["numeroCarta"].value;
    let ccv = document.forms["inserisci-pagamento"]["ccv"].value;
    let numerionly = /^[0-9]+$/;
    let c = 0;

    document.getElementById("cartaP").innerText = "";
    document.getElementById("ccvP").innerText = "";


    if (carta.match(numerionly)) {
        if (carta.length > 16) {
            document.getElementById("cartaP").innerText = "Il numero carta ha massimo 16 cifre.";
            c++;
        }
    } else {
        document.getElementById("cartaP").innerText = "Il numero carta deve essere formato solamente da cifre.";
        c++;
    }


    if (ccv.match(numerionly)){
        if (ccv.length != 3) {
            document.getElementById("ccvP").innerText = "Il ccv contiene 3 cifre.";
            c++;
        }
    } else {
        document.getElementById("ccvP").innerText = "Il ccv deve essere formato solamente da cifre";
        c++;
    }

    if (c > 0)
        return false;
    else
        return true;

}