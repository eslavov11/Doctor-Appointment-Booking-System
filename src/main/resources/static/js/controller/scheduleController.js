/**
 * Created by Edi on 26-Apr-17.
 */
var app = app || {};

app.scheduleController = function () {
    function ScheduleController(model, appointmentModel, viewBag) {
        this._model = model;
        this._appointmentModel = appointmentModel;
        this._viewBag = viewBag;
    }

    ScheduleController.prototype.loadSchedule = function () {
        var _this = this;
        this._model.getWeekSchedule()
            .then(function (data) {
                _this._viewBag.showSchedule(data);
                _this.updateAppointments();
            }, function (error) {
                console.log(error);
            });
    };

    ScheduleController.prototype.updateSchedule = function () {
        this._viewBag.updateSchedule();
    };

    ScheduleController.prototype.updateAppointments = function () {
        this._viewBag.updateAppointments();
    };

    return {
        load: function (model, appointmentModel, viewBag) {
            return new ScheduleController(model, appointmentModel, viewBag);
        }
    }
}();