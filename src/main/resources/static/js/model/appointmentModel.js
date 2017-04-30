/**
 * Created by Edi on 26-Apr-17.
 */

var app = app || {};

app.appointmentModel = (function() {
    function AppointmentModel(requester) {
        this._requester = requester;
        this._serviceUrl = '/appointment/'
    }

    AppointmentModel.prototype.getBookedAppointmentsForDay = function (date) {
        var queryUrl = this._serviceUrl + 'getForDate?date=' + date;

        return this._requester.get(queryUrl, date);
    };

    return {
        load: function(requester) {
            return new AppointmentModel(requester);
        }
    }
}());