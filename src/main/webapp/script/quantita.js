function updateQuantita(i){

    var quantita = "quantita"+i;
    var isbn = "isbn"+i;
    var prezzo = "prezzo"+i;

    var xhttp = new XMLHttpRequest();
    var quantita = document.getElementById(quantita).value;
    var isbn = document.getElementById(isbn).value;

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var xmlDoc = this.responseXML;
            var xmlLibro = xmlDoc.getElementsByTagName("libro");
            var totalexml = xmlLibro[0].getElementsByTagName("prezzoTotale")[0].childNodes[0].nodeValue
            var dettaglioxml = xmlLibro[0].getElementsByTagName("prezzoDettaglio")[0].childNodes[0].nodeValue
            document.getElementById("totale").innerHTML = totalexml;
            document.getElementById(prezzo).innerHTML = dettaglioxml;
        }
    }

    xhttp.open("GET","modifica-quantita?quantita="+quantita+"&isbn="+isbn);
    xhttp.send();
}