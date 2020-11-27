let ctx;
const ajaxUrl = "admin/users/";

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    ctx = {
        ajaxUrl: ajaxUrl,
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
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
                    "data": "enabled"
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
                    0,
                    "asc"
                ]
            ]
        })
    };
    makeEditable(ctx);
});

function changeEnabled(userId, value) {
    $.ajax({
        type: "POST",
        url: ajaxUrl + userId,
        data: "value=" + value
    }).done(function () {
        $.get(ajaxUrl, updateTable);
        let textMsg = "User " + userId;
        successNoty(value ? textMsg + " enabled" : textMsg + " disabled");
    });
}