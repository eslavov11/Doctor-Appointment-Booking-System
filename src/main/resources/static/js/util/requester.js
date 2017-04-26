/**
 * Created by Edi on 26-Apr-17.
 */

var app = app || {};

app.requester = (function () {
    function Requester() {}

    Requester.prototype.get = function (url) {
        return makeRequest('GET', url, null);
    };

    Requester.prototype.post = function (url, data) {
        data = JSON.stringify(data);

        return makeRequest('POST', url, data, true);
    };

    Requester.prototype.postPlain = function (url, data) {
        return makeRequest('POST', url, data, false);
    };

    Requester.prototype.put = function (url, data) {
        data = JSON.stringify(data);

        return makeRequest('PUT', url, data, true);
    };

    Requester.prototype.remove = function (url) {
        return makeRequest('DELETE', url, null);
    };

    function makeRequest(method, url, data, sendingJSON) {
        var defer = jQuery.Deferred();

        $.ajax({
            type: method,
            url: url,
            contentType: "charset=UTF-8; " + sendingJSON ? "application/json;" : "application/x-www-form-urlencoded;",
            data: data || null,
            beforeSend: function(xhr) {
                if(method !== 'GET') {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    if (typeof token != "undefined" && typeof header != "undefined") {
                        xhr.setRequestHeader(header, token);
                    }
                }
            },
            success: function (data) {
                defer.resolve(data);
            },
            error: function (error) {
                defer.reject(error);
            }
        });

        return defer.promise();
    }

    return {
        load: function () {
            return new Requester();
        }
    }
}());
