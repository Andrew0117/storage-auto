
function post(url_, json_) {
    let result = null;
    $.ajax({
        url: url_,
        contentType: "application/json; charset=utf-8",
        type: "POST",
        data: json_,
        dataType: 'json',
        async: false,
        delay: 250,
        success: function (data) {
            result = data;
        }
    });

    return result;
}

function get(url_) {
    let result = null;
    $.ajax({
        url: url_,
        contentType: "application/json; charset=utf-8",
        type: "GET",
        dataType: 'json',
        async: false,
        delay: 250,
        success: function (data) {
            result = data;
        }
    });

    return result;
}

function delete_(url_) {
    $.ajax({
        url: url_,
        contentType: "application/json; charset=utf-8",
        type: "DELETE",
        data: null,
        dataType: 'json',
        async: false,
        delay: 250
    });
}


