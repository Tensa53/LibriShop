function navbar() {
    var link = location.pathname.split("/")[2]; //preleva dalla path il nome della pagina
    console.log(link);

    if(link.includes("."))
        link = link.split(".")[0]; //nel caso di pagina con nome puntato, splitta l'estensione e prende solo il nome

    var element = document.getElementById(link); //preleva l'elemento dal DOM che ha il seguente id
    console.log(element);
    element.classList.add("active"); //aggiunge al relativo elemento la classe attiva per evidenziare il link della pagina corrente

}

//lancia lo script quando la pagina è stata caricata
window.onload = function() {
    navbar();
}
