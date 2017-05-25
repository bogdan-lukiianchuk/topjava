function makeEditable() {
    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

//just set id = null
function add() {
    $('#id').val(null);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id.slice(3),
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
            // $("tr .row").css({"background-color": "yellow"})
        });
        datatableApi.draw();
        var indexes = datatableApi.rows().eq( 0 ).filter( function (rowIdx) {
                return datatableApi.cell(rowIdx, 4).data()===false;
            });
        datatableApi.rows( indexes )
            .nodes()
            .to$()
            .addClass( 'info' );
/*
        datatableApi.rows().every( function (rowIdx, p2, p3) {
            var data = this.data();
            this.css({"background-color": "yellow"});
        });
*/
        // datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: 1500
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
