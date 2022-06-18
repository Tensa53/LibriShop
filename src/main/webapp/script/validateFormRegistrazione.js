function validateFormRegistrazione(){

    let nome = document.forms["Registrazione"]["nomer"].value;
    let cognome = document.forms["Registrazione"]["cognomer"].value;
    let mail = document.forms["Registrazione"]["mailr"].value;
    let password = document.forms["Registrazione"]["passwordr"].value;
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    // nome
    //lunghezza
    if (nome > 20){
        alert("Il campo nome non può superare i 20 caratteri.")
        return false;
    }

    // cognome
    //lunghezza
    if (cognome > 20){
        alert("Il campo cognome non può superare i 20 caratteri.")
        return false;
    }

    // mail
    //lunghezza
    if (mail > 30){
        alert("Il campo mail non può superare i 30 caratteri.")
        return false;
    }
    //formato
    if (!mail.match(mailformat)){
        alert("Il formato dell'email deve essere del tipo nomecasella@tuodominio.it")
        return false;
    }

    // password
    //lunghezza
    if (password > 40){
        alert("Il campo password non può superare i 40 caratteri.")
        return false;
    }

    // i campi sono tutti corretti
    return true;
}