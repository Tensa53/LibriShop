function validateFormModUtente(){

    let nome = document.forms["ModUtente"]["nome"].value;
    let cognome = document.forms["ModUtente"]["cognome"].value;
    let username = document.forms["ModUtente"]["username"].value;
    let password = document.forms["ModUtente"]["password"].value;
    let c = 0;

    document.getElementById("nomeP").innerText = "";
    document.getElementById("cognomeP").innerText = "";
    document.getElementById("usernameP").innerText = "";
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

    // password
    //lunghezza
    if (password.length > 40){ c++;
        document.getElementById("passwordP").innerText = "Il campo password non può superare i 40 caratteri."
    }

    if (username.length > 20){ c++;
        document.getElementById("usernameP").innerText = "Il campo username non può superare i 20 caratteri."
    }

    if (c>0){
        return false;
    }
    return true;
}