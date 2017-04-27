/**
 * Created by Edi on 26-Apr-17.
 */
var app = app || {};

app.scheduleViewBag = (function() {
    function showSchedule(data) {
        var scheduleDays = $('#schedule-table .schedule-day');
        scheduleDays.each(function (index, scheduleDay) {
            appendAppointmentTimes(scheduleDay, data.editDayScheduleModels[index], data.appointmentDuration);
        });
    }

    function appendAppointmentTimes(scheduleDay, dayScheduleData, appointmentDuration) {
        var startTime = dayScheduleData.startTimeStr.substr(0, 5).split(':');
        var endTime = dayScheduleData.endTimeStr.substr(0, 5).split(':');
        var startTimeMinutes = Number(startTime[0]) * 60 + Number(startTime[1]);
        var endTimeMinutes = Number(endTime[0]) * 60 + Number(endTime[1]);

        var currentAppointmentTime = startTimeMinutes;
        while(currentAppointmentTime < endTimeMinutes) {
            var hours = Math.floor(currentAppointmentTime / 60);
            var minutes = currentAppointmentTime % 60;
            var time = hours + ':' + minutes;

            var appointmentEl = $('<div>');
            var appointmentLink = $('<a>').text(time)
                .attr('href', '/appointment/add?date=' + new Date().toLocaleString("en-US").replace(',', ''));

            $(appointmentEl).append(appointmentLink);
            $(scheduleDay).append(appointmentEl);

            currentAppointmentTime += appointmentDuration;
        }
    }

    return {
        load: function () {
            return {
                showSchedule: showSchedule
            }
        }
    }
}());