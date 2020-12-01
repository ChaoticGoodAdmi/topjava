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
        }),
        update: () => $.get(ajaxUrl, updateTable)
    };
    makeEditable(ctx);
});

function changeEnabled(userId, checkbox) {
    let newValue = checkbox.is(":checked");
    $.ajax({
        type: "POST",
        url: ajaxUrl + userId,
        data: "enabled=" + newValue
    }).done(() => {
        let rowClassList = checkbox.closest("tr").get(0).classList;
        if (newValue) {
            rowClassList.remove("text-muted");
        } else {
            rowClassList.add("text-muted");
        }
        let textMsg = "User " + userId;
        successNoty(newValue ? textMsg + " enabled" : textMsg + " disabled");
    }).fail(() => {
        $(checkbox).prop("checked", !newValue);
    });
}