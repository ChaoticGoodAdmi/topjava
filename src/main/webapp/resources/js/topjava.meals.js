let ctx;
let mealAjaxUrl = "profile/meals/";

let startDate = $("#startDate");
let endDate = $("#endDate");
let startTime = $("#startTime");
let endTime = $("#endTime");

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
                $(row).attr("data-mealExcess", data.excess);
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
    format: "Y-m-d",
    onShow() {
        this.setOptions({
            maxDate: endDate.val() ? endDate.val() : false
        })
    }
});

$('#endDate').datetimepicker({
    timepicker: false,
    format: "Y-m-d",
    onShow() {
        this.setOptions({
            minDate: startDate.val() ? startDate.val() : false
        })
    }
});

$('#startTime').datetimepicker({
    datepicker: false,
    format: "H:i",
    onShow() {
        this.setOptions({
            maxTime: endTime.val() ? endTime.val() : false
        })
    }
});

$('#endTime').datetimepicker({
    datepicker: false,
    format: "H:i",
    onShow() {
        this.setOptions({
            minTime: startTime.val() ? startTime.val() : false
        })
    }
});