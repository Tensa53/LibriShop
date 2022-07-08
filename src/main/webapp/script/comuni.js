function comuni (id) {
    var provincia = id;

    $.ajax({
        "url": "comuni",
        "data": "id="+provincia,
        "success": function() {
            document.getElementById("comuner").innerHTML = this.responseText;
        }
    });
}