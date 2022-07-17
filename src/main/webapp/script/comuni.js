function comuni (provincia) {

    if (provincia != 0) {
        $.ajax({
            type : "GET",
            url : "comuni",
            data : "provincia="+provincia,
            success: function(result) {
                $('#cittar').html(result);
            }
        });
    } else
        $('#cittar').html('');
}