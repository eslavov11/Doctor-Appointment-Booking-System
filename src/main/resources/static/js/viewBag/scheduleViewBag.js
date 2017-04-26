/**
 * Created by Edi on 26-Apr-17.
 */
var app = app || {};

app.scheduleViewBag = (function() {
    function showSchedule(data) {
        console.log(data);
    }

    return {
        load: function () {
            return {
                showSchedule: showSchedule
            }
        }
    }
}());