function validateFormInserisciPagamento(){
    let carta = document.forms["inserisci-pagamento"]["numeroCartar"].value;
    let numerionly = /^[0-9]+$/;

    if (!carta.match(numerionly)){
        document.getElementById("cartaP").innerText = "La carta può contenere solo numeri.";
        return false;
    }

    return true;

}