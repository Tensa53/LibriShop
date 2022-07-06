function validateFormModificaPagamento(){
    let carta = document.forms["modifica-pagamento"]["numeroCarta"].value;
    let numerionly = /^[0-9]+$/;

    if (!carta.match(numerionly)){
        document.getElementById("cartaP2").innerText = "La carta può contenere solo numeri.";
        return false;
    }

    return true;

}