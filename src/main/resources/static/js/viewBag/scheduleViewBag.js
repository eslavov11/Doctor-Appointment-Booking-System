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

        var currentMonday = app.getDateOfISOWeek(weekYear[0], weekYear[1]);
        var dateOfWeek = new Date(currentMonday.getTime());;

        $('.schedule-date').each(function (index, scheduleDate) {
            var formattedDate = ('0' + dateOfWeek.getDate()).slice(-2) + '.' + ('0' + (dateOfWeek.getMonth() + 1)).slice(-2);

            $(scheduleDate).last()
                .text(formattedDate)
                .attr('data-content', dateOfWeek.toLocaleDateString("en-US"));

            dateOfWeek.setDate(dateOfWeek.getDate() + 1);
        });

        return currentMonday;
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

    function clearBookedAppointments() {
        $('#schedule-table .schedule-day .disabled-link').css('color', '').removeClass('disabled-link');
    }

    function showBookedAppointmentsForDay(data) {
        if (data.length != 0) {
            var dayOfWeek = new Date(data[0].date).getDay();
            dayOfWeek = (dayOfWeek == 0) ? 7 : dayOfWeek;
            dayOfWeek--; // 0 - monday, .. 6 - sunday

            $('#schedule-table .schedule-day').eq(dayOfWeek)
                .find('.appointment-wrapper').each(function (index, appointmentWrapper) {
                    var appointmentLinkEl = $(appointmentWrapper).find('a')[0];

                    var time = $(appointmentLinkEl).text().split(':'),
                        hour = Number(time[0]),
                        minute = Number(time[1]),
                        hasAppointment = false;

                    data.forEach(function (appointment) {
                        var appDate = new Date(appointment.date);

                        if (hour === appDate.getHours() && minute === appDate.getMinutes()) {
                            $(appointmentLinkEl).css('color', 'red').addClass('disabled-link');

                            hasAppointment = true;
                            return false;
                        }
                    });

                    /*if ($(appointmentLinkEl).hasClass('disabled-link') && !hasAppointment) {
                        $(appointmentLinkEl).css('color', '').removeClass('disabled-link');
                    }*/
                });
        }
    }

    return {
        load: function () {
            return {
                showSchedule: showSchedule,
                showBookedAppointmentsForDay: showBookedAppointmentsForDay,
                clearBookedAppointments: clearBookedAppointments,
                updateSchedule: updateSchedule,
                updateAppointments: updateAppointments
            }
        }
    }
}());