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
            var appointmentLink = $('<a>').text(time);

            if ($('#role-doctor').length) {
                $(appointmentLink).attr('href', '/appointment/doctor/add')
                // + new Date().toLocaleString("en-US").replace(',', '')
                    .attr('data-content', ('/appointment/doctor/add?date={0} ' + app.convert24To12HourFormat(time + ':00')));
            } else {
                $(appointmentLink).attr('href', '/appointment/patient/add')
                    .attr('data-content', ('/appointment/patient/add?date={0} ' + app.convert24To12HourFormat(time + ':00')));
            }

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

        var dateOfWeek = new Date(currentMonday.getTime());

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
        _checkForPastWeek($('#schedule-table .schedule-date').first().attr('data-content'));

        $('#schedule-table .schedule-date').each(function (index, scheduleDate) {
            var appointmentDate = $(scheduleDate).last().attr('data-content');

            var scheduleDay = $('#schedule-table .schedule-day').eq(index);
            scheduleDay.find('a').each(function (index, appointmentLinkEl) {
                var hrefFormatted = $(appointmentLinkEl).attr('data-content').format(appointmentDate);

                $(appointmentLinkEl).attr('href', hrefFormatted);
            });
        });
    }

    function clearBookedAppointments() {
        $('#schedule-table .schedule-day .appointment-booked').css('color', '').removeClass('disabled-link');
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
                        if ($('#role-doctor').length) {
                            var href = $(appointmentLinkEl).attr('href').split('?');

                            $(appointmentLinkEl)
                                .css('color', 'red')
                                .addClass('appointment-booked')
                                .removeClass('disabled-link')
                                .attr('href', ('/appointment/?' + href[1]));
                        } else {
                            $(appointmentLinkEl)
                                .css('color', 'red')
                                .addClass('disabled-link')
                                .addClass('appointment-booked');
                        }

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
    
    function _checkForPastWeek(scheduleDate) {
        var dateToCompare = new Date(new Date(scheduleDate).getTime() + 6 * 24 * 60 * 60 * 1000);
        // Resets all appointments if week is past
        if (dateToCompare < new Date()) {
            $('#schedule-table .schedule-day').find('a')
                .addClass('disabled-link')
                .addClass('appointment-booked')
                .css('color', 'black');
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