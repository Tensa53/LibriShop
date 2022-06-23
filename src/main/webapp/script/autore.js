function ControllaAutore(){
        let xhttp = new XMLHttpRequest();
        let input = document.getElementById("autore").value;

        if(input != "") {

            xhttp.onreadystatechange = function () {
                if(this.readyState == 4 && this.status == 200) {
                    document.getElementById("controlloautore").innerHTML = this.responseText;
                }
            }

            xhttp.open("GET","autore?autore="+input, true);
            xhttp.send();
        }

        else
            document.getElementById("controlloautore").innerHTML = "";

}