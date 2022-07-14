function validateFormUtente(name) {

    console.log(name)

    let nome = document.forms[name]["nome"].value;
    let cognome = document.forms[name]["cognome"].value;
    let username = document.forms[name]["username"].value;
    let password = document.forms[name]["password"].value;
    const passwordformat = new RegExp('(?=.*[!@#$%^&*])(?=.*\\d)(?=.*[A-Z]).{8,}');
    let c = 0;


    if (name === 'Registrazione') {
        let mail = document.forms[name]["mail"].value;
        document.getElementById("mailP").innerText = "";

        // mail
        //lunghezza
        if (mail.length > 30) {
            c++;
            document.getElementById("mailP").innerText = "Il campo mail non può superare i 30 caratteri.";
        }

        //formato
        let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (!mail.match(mailformat)) {
            c++;
            document.getElementById("controllomailregx").innerText = "Il formato dell'email deve essere del tipo nomecasella@tuodominio.it";
        }

    }

    document.getElementById("nomeP").innerText = "";
    document.getElementById("cognomeP").innerText = "";
    document.getElementById("usernameP").innerText = "";
    document.getElementById("passwordP").innerText = "";

    // nome
    //lunghezza
    if (nome.length > 20) {
        c++;
        document.getElementById("nomeP").innerText = "non può superare 20 caratteri.";
    }

    // cognome
    //lunghezza
    if (cognome.length > 20) {
        c++;
        document.getElementById("cognomeP").innerText = "non può superare 20 caratteri.";
    }

    // password
    //lunghezza
    if (password.length > 40) {
        c++;
        document.getElementById("passwordP").innerText = "Il campo password non può superare i 40 caratteri."
    }

    if(!password.match(passwordformat)){
        c++;
        document.getElementById("controlloPassword").innerText = "Rispetta il formato della password";
    }

    if (username.length > 20) {
        c++;
        document.getElementById("usernameP").innerText = "Il campo username non può superare i 20 caratteri."
    }

    console.log(c)

    if (c > 0) {
        return false;
    }
    return true;
}