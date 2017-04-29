/**
 * Created by Edi on 26-Apr-17.
 */
var app = app || {};

app.scheduleViewBag = (function () {
    function showSchedule(data) {
        var scheduleDays = $('#schedule-table .schedule-day');
        scheduleDays.each(function (index, scheduleDay) {
            _appendAppointmentTimes(scheduleDay, data.editDayScheduleModels[index], data.appointmentDuration);
        });
    }

    function _appendAppointmentTimes(scheduleDay, dayScheduleData, appointmentDuration) {
        var startTime = dayScheduleData.startTimeStr.substr(0, 5).split(':');
        var endTime = dayScheduleData.endTimeStr.substr(0, 5).split(':');
        var startTimeMinutes = Number(startTime[0]) * 60 + Number(startTime[1]);
        var endTimeMinutes = Number(endTime[0]) * 60 + Number(endTime[1]);

        var currentAppointmentTime = startTimeMinutes;
        var appointmentsCount = 0;
        while (currentAppointmentTime < endTimeMinutes) {
            var hours = Math.floor(currentAppointmentTime / 60);
            var minutes = currentAppointmentTime % 60;
            var time = ('0' + hours).slice(-2) + ':' +
                ('0' + minutes).slice(-2);

            var appointmentEl = $('<span>').addClass('appointment-wrapper');
            var appointmentLink = $('<a>').text(time)
                .attr('href', '/appointment/add')
                // + new Date().toLocaleString("en-US").replace(',', '')
                .attr('data-content', ('/appointment/add?date={0} ' + app.convert24To12HourFormat(time + ':00')));

            $(appointmentEl).append(appointmentLink);
            $(scheduleDay).append(appointmentEl);

            currentAppointmentTime += appointmentDuration;

            if (appointmentsCount % 2 == 0) {
                $(scheduleDay).append(' ');
                $(scheduleDay).append($('<span>').text('| ').addClass('hidden-sm-down'));
            } else {
                $(scheduleDay).append($('<br>'));
            }

            ++appointmentsCount;
        }

        if (appointmentsCount == 0) {
            $(scheduleDay).append('Day off.');
        }
    }

    function updateSchedule() {
        var weekYear = $('#week-input').val().split('-W');

        var dateOfWeek = app.getDateOfISOWeek(weekYear[0], weekYear[1]);

        $('.schedule-date').each(function (index, scheduleDate) {
            var formattedDate = ('0' + dateOfWeek.getDate()).slice(-2) + '.' + ('0' + (dateOfWeek.getMonth() + 1)).slice(-2);

            $(scheduleDate).last()
                .text(formattedDate)
                .attr('data-content', dateOfWeek.toLocaleDateString("en-US"));

            dateOfWeek.setDate(dateOfWeek.getDate() + 1);
        });
    }

    function updateAppointments() {
        $('.schedule-date').each(function (index, scheduleDate) {
            var appointmentDate = $(scheduleDate).last().attr('data-content');

            var scheduleDay = $('#schedule-table .schedule-day').eq(index);
            scheduleDay.find('a').each(function (index, appointmentLinkEl) {
                var hrefFormatted = $(appointmentLinkEl).attr('data-content').format(appointmentDate);

                $(appointmentLinkEl).attr('href', hrefFormatted);
            });
        });
    }

    function loadBookedAppointments() {
        $('.schedule-date').each(function (index, scheduleDate) {
            var appointmentDate = $(scheduleDate).last().attr('data-content');

            var scheduleDay = $('#schedule-table .schedule-day').eq(index);
            scheduleDay.find('a').each(function (index, appointmentLinkEl) {
                var hrefFormatted = $(appointmentLinkEl).attr('data-content').format(appointmentDate);

                $(appointmentLinkEl).attr('href', hrefFormatted);
            });
        });
    }

    return {
        load: function () {
            return {
                showSchedule: showSchedule,
                updateSchedule: updateSchedule,
                updateAppointments: updateAppointments
            }
        }
    }
}());