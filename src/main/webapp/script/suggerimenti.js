function getTitoli() {
    var xhttp = new XMLHttpRequest();
    var stringa = document.getElementById("ricerca-ajax").value;//preleva dall'elemento col seguente id il valore di testo al suo interno

    xhttp.onreadystatechange = function () { //quando l'elemento selezionato è nello stato in cui è in grado di invocare la funzione
        if(this.readyState == 4 && this.status == 200) { //se l'operazione asincrona è stata completata (4) e non ci sono errori (200)
            document.getElementById("titoli").innerHTML = this.responseText; //in questo caso scrivi nel dom i nuovi tag html in ricezione dalla risposta del server
        }
    }

    xhttp.open("GET","ricerca-ajax?titolo="+stringa, true);//inizializza una richiesta di tipo GET con i relativi paramteri (titolo) e definisce async a true (chiamata asincrona)
    xhttp.send();//invia la richiesta
}