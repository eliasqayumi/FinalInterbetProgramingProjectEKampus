<%--
  Created by IntelliJ IDEA.
  User: qayumi
  Date: 12/10/21
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="l" dir="ltr">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="index.css">
    <title></title>
</head>
<body>
<div class="container">
    <div class="image">
        <img src="galaxy.jpeg" alt="">
    </div>
    <form class="login" action="<%=request.getContextPath()%>/login" method="post">
        <h1>login</h1>
        <small>${message}</small>
        <input type="text" class="inputs" name="username" value="${username}"
               placeholder="username" required>
        <hr>
        <input type="password" class="inputs" name="password" placeholder="password" required>
        <hr>
        <input type="submit" class="button" name="submit" value="login">
        <div class="footerOfForm">
            <label for="checkbox">
                <input type="checkbox" id="checkbox" name="remember">
                remember me
            </label>
            <a href="#">forgot password</a>
        </div>
    </form>
</div>
<div class="footer">
    <svg xmlns:xlink="http://www.w3.org/1999/xlink" id="wave" style="transform:rotate(0deg); transition: 0.3s"
         viewBox="0 0 1440 310" version="1.1" xmlns="http://www.w3.org/2000/svg">
        <defs>
            <linearGradient id="sw-gradient-0" x1="0" x2="0" y1="1" y2="0">
                <stop stop-color="rgba(181.432, 62, 243, 1)" offset="0%"/>
                <stop stop-color="rgba(11, 81.428, 255, 1)" offset="100%"/>
            </linearGradient>
        </defs>
        <path style="transform:translate(0, 0px); opacity:1" fill="url(#sw-gradient-0)"
              d="M0,0L26.7,36.2C53.3,72,107,145,160,155C213.3,165,267,114,320,108.5C373.3,103,427,145,480,149.8C533.3,155,587,124,640,139.5C693.3,155,747,217,800,227.3C853.3,238,907,196,960,180.8C1013.3,165,1067,176,1120,191.2C1173.3,207,1227,227,1280,201.5C1333.3,176,1387,103,1440,62C1493.3,21,1547,10,1600,25.8C1653.3,41,1707,83,1760,108.5C1813.3,134,1867,145,1920,134.3C1973.3,124,2027,93,2080,108.5C2133.3,124,2187,186,2240,175.7C2293.3,165,2347,83,2400,41.3C2453.3,0,2507,0,2560,36.2C2613.3,72,2667,145,2720,170.5C2773.3,196,2827,176,2880,149.8C2933.3,124,2987,93,3040,72.3C3093.3,52,3147,41,3200,31C3253.3,21,3307,10,3360,10.3C3413.3,10,3467,21,3520,25.8C3573.3,31,3627,31,3680,72.3C3733.3,114,3787,196,3813,237.7L3840,279L3840,310L3813.3,310C3786.7,310,3733,310,3680,310C3626.7,310,3573,310,3520,310C3466.7,310,3413,310,3360,310C3306.7,310,3253,310,3200,310C3146.7,310,3093,310,3040,310C2986.7,310,2933,310,2880,310C2826.7,310,2773,310,2720,310C2666.7,310,2613,310,2560,310C2506.7,310,2453,310,2400,310C2346.7,310,2293,310,2240,310C2186.7,310,2133,310,2080,310C2026.7,310,1973,310,1920,310C1866.7,310,1813,310,1760,310C1706.7,310,1653,310,1600,310C1546.7,310,1493,310,1440,310C1386.7,310,1333,310,1280,310C1226.7,310,1173,310,1120,310C1066.7,310,1013,310,960,310C906.7,310,853,310,800,310C746.7,310,693,310,640,310C586.7,310,533,310,480,310C426.7,310,373,310,320,310C266.7,310,213,310,160,310C106.7,310,53,310,27,310L0,310Z"/>
    </svg>
</div>

</body>
</html>
