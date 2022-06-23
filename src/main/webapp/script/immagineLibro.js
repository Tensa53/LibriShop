function immagineLibro(isbn) {

    let codice = isbn;
    let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            if(this.readyState == 4 && this.status == 200) {
                document.getElementById("fotolibro").src = this.responseText;
            }
        }

        xhttp.open("GET","immaginelibro?isbn="+codice, true);
        xhttp.send();

}