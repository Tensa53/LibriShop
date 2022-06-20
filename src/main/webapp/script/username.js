function ControllaUsername() {
    let xhttp = new XMLHttpRequest();
    let input = document.getElementById("controlla-username").value;

    if(input != "") {

        xhttp.onreadystatechange = function () {
            if(this.readyState == 4 && this.status == 200) {
                document.getElementById("controllousername").innerHTML = this.responseText;
            }
        }

        xhttp.open("GET","controlla-username?username="+input, true);
        xhttp.send();
    }

    else
        document.getElementById("controllousername").innerHTML = "";

}