function navbar() {
    var pathParts = location.pathname.split("/").filter(part => part !== ""); //rimuove elementi vuoti
    var link = pathParts[pathParts.length - 1]; //preleva l'ultimo elemento della path (nome della pagina)
    console.log(link);

    if(link.includes("."))
        link = link.split(".")[0]; //nel caso di pagina con nome puntato, splitta l'estensione e prende solo il nome

    var element = document.getElementById(link); //preleva l'elemento dal DOM che ha il seguente id
    if(element != null)
        element.classList.add("active"); //aggiunge al relativo elemento la classe attiva per evidenziare il link della pagina corrente

}

//lancia lo script quando la pagina è stata caricata
window.onload = function() {
    navbar();
}
