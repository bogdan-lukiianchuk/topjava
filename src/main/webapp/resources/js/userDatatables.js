var ajaxUrl = 'ajax/admin/users/';
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "id",
                "visible": false
            },
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "type": "checkbox",
                "render": function (data, type, full, meta) {
                    if (data === true || data === "true"){
                        return '<input type="checkbox" checked onchange="changeActive('+full.id+', '+false+')"/>';
                    } else {
                        return '<input type="checkbox" onchange="changeActive('+full.id+', '+true+')"/>';
                    }
                }
            },
            {
                "data": "registered"
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
                1,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function changeActive(id, newstate) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: {
            "enabled": newstate
        },
        success: function () {
            updateTable();
            successNoty('User Enabled/Disabled');
        }
    });

}