/**
 * Created by Edi on 26-Apr-17.
 */

var app = app || {};

app.weekScheduleModel = (function() {
    function WeekScheduleModel(requester) {
        this._requester = requester;
        this._serviceUrl = '/appointment/'
    }

    WeekScheduleModel.prototype.getWeekSchedule = function (date) {
        var queryUrl = this._serviceUrl + 'getForDate?date=' + date;

        return this._requester.get(queryUrl, date);
    };

    return {
        load: function(requester) {
            return new WeekScheduleModel(requester);
        }
    }
}());