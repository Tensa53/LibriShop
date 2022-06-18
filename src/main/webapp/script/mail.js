function ControllaMail() {
   let xhttp = new XMLHttpRequest();
   let input = document.getElementById("controlla-mail").value;

    if(input != "") {

        xhttp.onreadystatechange = function () {
            if(this.readyState == 4 && this.status == 200) {
                document.getElementById("controllomail").innerHTML = this.responseText;
            }
        }

       xhttp.open("GET","controlla-mail?mail="+input, true);
       xhttp.send();
    }

    else
        document.getElementById("controllomail").innerHTML = "";

}