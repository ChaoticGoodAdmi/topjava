let ctx;

$(function () {
    ctx = {
        ajaxUrl: "user/meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
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
        }),
        update: filter
    };
    makeEditable(ctx);
});

function filter() {
    $.ajax({
        type: "GET",
        url: "user/meals/filter",
        data: $('#filter').serialize()
    }).done(updateTable);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ctx.ajaxUrl, updateTable);
}