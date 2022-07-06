function validateFormLogin(){

    let mail = document.forms["login"]["mail"].value;
    let password = document.forms["login"]["password"].value;
    let c=0;

    document.getElementById("mailP").innerText = "";
    document.getElementById("passwordP").innerText = "";

    if (mail.length > 30){ c++;
        document.getElementById("mailP").innerText = "Il campo mail non può superare i 30 caratteri.";
    }
    let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!mail.match(mailformat)){ c++;
        document.getElementById("mailP").innerText = "Il formato dell'email deve essere del tipo nomecasella@tuodominio.it";
    }

    if (password.length > 40){ c++;
        document.getElementById("passwordP").innerText = "Il campo password non può superare i 40 caratteri."
    }

    if (c>0){
        return false;
    }
    return true;

}