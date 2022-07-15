function validateFormGenere() {
    let genere = document.forms["inseriscigen"]["genere"].value;

    let genereP = document.getElementById("genereP");

    genereP.innerText = "";

    if (genere.length > 20){
        genereP.innerText = "Il genere può avere massimo 20 caratteri";
        return false;
    } else
        return true;
}