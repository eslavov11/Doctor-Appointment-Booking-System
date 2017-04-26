/**
 * Created by Edi on 26-Apr-17.
 */

var app = app || {};

app.weekScheduleModel = (function() {
    function WeekScheduleModel(requester) {
        this._requester = requester;
        this._serviceUrl = '/schedule/'
    }

    WeekScheduleModel.prototype.getWeekSchedule = function () {
        var queryUrl = this._serviceUrl + 'week';

        return this._requester.get(queryUrl);
    };

    return {
        load: function(requester) {
            return new WeekScheduleModel(requester);
        }
    }
}());
