function validateFormInserisciPagamento(){
    let carta = document.forms["inserisci-pagamento"]["numeroCartar"].value;
    let numerionly = /^[0-9]+$/;

    document.getElementById("cartaP").innerText = "";

    if (!carta.match(numerionly)){
        document.getElementById("cartaP").innerText = "La carta può contenere solo numeri.";
        return false;
    }

    return true;

}