function validateFormGenere() {
    let genere = document.forms["inseriscigen"]["genere"].value;

    let genereP = document.getElementById("genereP");

    genereP.innerText = "";

    if (genere.length > 40){
        genereP.innerText = "Il genere può avere massimo 40 caratteri";
        return false;
    } else
        return true;
}