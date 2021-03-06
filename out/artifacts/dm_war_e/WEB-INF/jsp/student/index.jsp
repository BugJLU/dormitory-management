<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/18
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Index</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body background="/img/background.jpg">
<%
    StudentInfo studentInfo = (StudentInfo) request.getSession().getAttribute("student");
//    if (studentInfo == null) return;
%>
<h1>Hello <%=studentInfo.getBase().getName()%>!</h1>

<a href="/logout">logout</a>

<div class="l-center">
<form action="/student/update" method="post">
    <table>
        <tr>
            <td>
                <label for="name">name</label>
            </td>
            <td>
                <input type="text" id="name" name="name"
                       value="<%=studentInfo.getBase().getName()%>">
            </td>
        </tr>
        <tr>
            <td>
                gender
            </td>
            <td>
                <% if (studentInfo.getGender() != null) { %>
                <%=studentInfo.getGender() == 0 ? "男" : "女"%>
                <% } %>
            </td>
        </tr>
        <tr>
            <td>
                <label for="phone">phone</label>
            </td>
            <td>
                <input type="text" id="phone" name="phone"
                       value="<%=studentInfo.getBase().getPhone()%>">
            </td>
        </tr>
        <tr>
            <td>
                <label for="studentId">student ID</label>
            </td>
            <td>
                <input type="text" id="studentId" name="studentId"
                       value="<%=studentInfo.getStudentId()%>">
            </td>
        </tr>
        <tr>
            <td>
                <p>room</p>
            </td>
            <td>
                <p><%=studentInfo.getRoom()!=null ?
                        studentInfo.getRoom().getRoomNum() :
                        "No room allotted."
                %></p>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="update info">
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
