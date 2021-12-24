
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="l" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="adminStyle.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <title>Teacher</title>
</head>
<body>
<header>
    <div class="navbar">
        <a href="/teachers">
            <i class="fas fa-university"></i>
        </a>
        <p>Cumhuriyet University</p>
        <a id="logout" href="/">Log out</a>
    </div>
</header>
<div class="body">
    <ul>
        <div class="panel-column">
            <li><a href="/student"><i class='fas fa-user-graduate'></i><span>Students</span></a></li>
            <li><a href="/score"><i class='fas fa-building'></i><span>Score</span></a></li>
            <li><a href="/teacher/subjects"><i class="fas fa-chalkboard-teacher"></i><span>Subjects</span></a></li>
        </div>
    </ul>
</div>
<footer>
    <svg xmlns:xlink="http://www.w3.org/1999/xlink" id="wave" style="transform:
    rotate(0deg); transition: 0.3s" viewBox="0 0 1440 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <defs><linearGradient id="sw-gradient-0" x1="0" x2="0" y1="1" y2="0">
            <stop stop-color="rgba(71.007, 30.632, 17.664, 1)" offset="0%"/>
            <stop stop-color="rgba(11, 69.03, 255, 1)" offset="700%"/>
        </linearGradient></defs><path style="transform:translate(0, 0px);
    opacity:1" fill="url(#sw-gradient-0)" d="M0,20L16,31.7C32,43,64,67,96,70C128,73,160,57,192,45C224,
    33,256,27,288,35C320,43,352,67,384,76.7C416,87,448,83,480,78.3C512,73,544,67,576,53.3C608,40,640,
    20,672,10C704,0,736,0,768,13.3C800,27,832,53,864,58.3C896,63,928,47,960,46.7C992,47,1024,63,1056,
    73.3C1088,83,1120,87,1152,85C1184,83,1216,77,1248,71.7C1280,67,1312,63,1344,63.3C1376,63,1408,67,
    1440,68.3C1472,70,1504,70,1536,66.7C1568,63,1600,57,1632,60C1664,63,1696,77,1728,68.3C1760,60,1792,
    30,1824,26.7C1856,23,1888,47,1920,46.7C1952,47,1984,23,2016,21.7C2048,20,2080,40,2112,41.7C2144,43,
    2176,27,2208,23.3C2240,20,2272,30,2288,35L2304,40L2304,100L2288,100C2272,100,2240,100,2208,100C2176,100,
    2144,100,2112,100C2080,100,2048,100,2016,100C1984,100,1952,100,1920,100C1888,100,1856,100,1824,100C1792,
    100,1760,100,1728,100C1696,100,1664,100,1632,100C1600,100,1568,100,1536,100C1504,100,1472,100,1440,100C1408,
    100,1376,100,1344,100C1312,100,1280,100,1248,100C1216,100,1184,100,1152,100C1120,100,1088,100,1056,100C1024,
    100,992,100,960,100C928,100,896,100,864,100C832,100,800,100,768,100C736,100,704,100,672,100C640,100,608,100,
    576,100C544,100,512,100,480,100C448,100,416,100,384,100C352,100,320,100,288,100C256,100,224,100,192,100C160,
    100,128,100,96,100C64,100,32,100,16,100L0,100Z"/></svg>
</footer>



</body>
</html>
