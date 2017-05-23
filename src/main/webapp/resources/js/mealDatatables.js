var ajaxUrl = 'ajax/meals/';
var datatableApi;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function(data, type, row) {
                    //moment.js required
                    return moment(data).format('LLL');
                },
                "type": "moment-js-date"

            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

function filter() {
    var form = $('#filterForm');
    $.ajax({
        url: ajaxUrl+'filter/',
        type: 'POST',
        data: form.serialize(),
        success: function () {
            updateTable();
            successNoty('Filter applied')
        }
    });
}

function clearFilter() {
    var form = $('#filterForm');
    $.ajax({
        url: ajaxUrl+'filterClear',
        type: 'POST',
        success: function () {
            updateTable();
            successNoty('Filter cleared')
        }
    });
}