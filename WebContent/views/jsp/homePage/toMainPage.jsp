<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Ace Admin</title>
    <meta http-equiv="imagetoolbar" content="no">
    <link href='${pageContext.request.contextPath}/resource/fullcalendar/fullcalendar.min.css' rel='stylesheet'/>
    <link href='${pageContext.request.contextPath}/resource/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print'/>
    <script src='${pageContext.request.contextPath}/resource/fullcalendar/lib/moment.min.js'></script>
    <script src='${pageContext.request.contextPath}/resource/fullcalendar/lib/jquery.min.js'></script>
    <script src='${pageContext.request.contextPath}/resource/fullcalendar/fullcalendar.min.js'></script>
    <script src='${pageContext.request.contextPath}/resource/fullcalendar/locale/zh-cn.js'></script>
    <script>

        $(document).ready(function () {

            $('#calendar').fullCalendar({
                defaultDate: new Date(),
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: '2017-05-01'
                    },
                    {
                        title: 'Long Event',
                        start: '2017-05-07',
                        end: '2017-05-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2017-05-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2017-05-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2017-05-11',
                        end: '2017-05-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2017-05-12T10:30:00',
                        end: '2017-05-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2017-05-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2017-05-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2017-05-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2017-05-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2017-05-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2017-05-28'
                    }
                ]
            });

        });

    </script>
    <style>
        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 100%;
            margin: 0 auto;
        }

    </style>
</head>

<body>
    <div id='calendar'></div>
</body>
</html>
