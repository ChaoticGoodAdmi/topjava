let ctx;
let mealAjaxUrl = "profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);

}
function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    ctx = {
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type) {
                        if (type === "display") {
                            return formatDate(date);
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data) {
                if (!data.enabled) {
                    $(row).attr("data-mealExcess", data.excess);
                }
            }
        }),
        updateTable: updateFilteredTable
    };
    makeEditable();
});

$('#dateTime').datetimepicker({
    format: 'Y-m-d H:i'
});

$('#startDate').datetimepicker({
    timepicker: false,
    format: "Y-m-d"
});
$('#endDate').datetimepicker({
    timepicker: false,
    format: "Y-m-d"
});
$('#startTime').datetimepicker({
    datepicker: false,
    format: "H:i"
});
$('#endTime').datetimepicker({
    datepicker: false,
    format: "H:i"
});