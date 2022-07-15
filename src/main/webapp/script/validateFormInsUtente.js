function validateFormInsUtente(){

    let nome = document.forms["registrazione"]["nomer"].value;
    let cognome = document.forms["registrazione"]["cognomer"].value;
    let mail = document.forms["registrazione"]["mailr"].value;
    let password = document.forms["registrazione"]["passwordr"].value;
    let c = 0;

    document.getElementById("nomeP").innerText = "";
    document.getElementById("cognomeP").innerText = "";
    document.getElementById("mailP").innerText = "";
    document.getElementById("passwordP").innerText = "";

    // nome
    //lunghezza
    if (nome.length > 20){ c++;
        document.getElementById("nomeP").innerText = "Il campo nome non può superare i 20 caratteri.";
    }

    // cognome
    //lunghezza
    if (cognome.length > 20){ c++;
        document.getElementById("cognomeP").innerText = "Il campo cognome non può superare i 20 caratteri.";
    }

    // mail
    //lunghezza
    if (mail.length > 30){ c++;
        document.getElementById("mailP").innerText = "Il campo mail non può superare i 30 caratteri.";
    }
    //formato
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!mail.match(mailformat)){ c++;
        document.getElementById("controllomail").innerText = "Il formato dell'email deve essere del tipo nomecasella@tuodominio.it";
    }

    // password
    //lunghezza
    if (password.length > 40){ c++;
        document.getElementById("passwordP").innerText = "Il campo password non può superare i 40 caratteri."
    }

    if (c>0){
        return false;
    }
    return true;
}