function validateFormModificaUtente(){

    let nome = document.forms["modifica-utente"]["nome"].value;
    let cognome = document.forms["modifica-utente"]["cognome"].value;
    let username = document.forms["modifica-utente"]["username"].value;
    let password = document.forms["modifica-utente"]["password"].value;
    let c = 0;

    document.getElementById("nomeP").innerText = "";
    document.getElementById("cognomeP").innerText = "";
    document.getElementById("usernameP").innerText = "";
    document.getElementById("passwordP").innerText = "";

    if (nome.length > 20){ c++;
        document.getElementById("nomeP").innerText = "Il campo nome non può superare i 20 caratteri.";
    }

    if (cognome.length > 20){ c++;
        document.getElementById("cognomeP").innerText = "Il campo cognome non può superare i 20 caratteri.";
    }

    if (password.length > 40){ c++;
        document.getElementById("nomeP").innerText = "Il campo password non può superare i 40 caratteri."
    }

    if (username.length > 20){ c++;
        document.getElementById("usernameP").innerText = "Il campo username non può superare i 20 caratteri."
    }

    if (c>0){
        return false;
    }
    return true;
}