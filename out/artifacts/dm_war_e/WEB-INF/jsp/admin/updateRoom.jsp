<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtliu.dormitorymanagement.model.StudentInfo" %>
<%@ page import="com.jtliu.dormitorymanagement.model.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="/admin/updateRoom">
</form>
<head>
    <title>UpdateRoom</title>
</head>
<link href="/css/layout.css" rel="stylesheet">

<body>

<a href="/admin/roomInfo">&lt;&lt; room info</a>

<h1>UpdateRoom：<%=request.getAttribute("roomNum")%></h1>

    <%
        List<StudentInfo> studentList = (List<StudentInfo>)request.getAttribute("studentList");
        if(studentList != null && studentList.size() != 0){
    %>
    <table>
        <tr>
            <a>StudentList:</a>
        </tr>
        <%
            for(StudentInfo ss : studentList){
        %>
        <tr>
           <td>
               <a><%=ss.getBase().getName()%></a>
           </td>
            <td>
                <a href="/admin/removeRoomFromStudent?sid=<%=ss.getId()%>">Remove</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }else{
    %>
    <h2>No student in this room.</h2>
    <%}%>

<table>
<form action="/admin/addRoomToStudent">
    <input type="text" id ="roomNum" name="roomNum" value="<%=request.getAttribute("roomNum")%>" style="visibility:hidden" />
    <tl>
        <td>
            <p>Add Student: </p>
        </td>

        <td>
            <select name="sid" id=sid>
                <option value="NoStudent"selected="selected">Select Student</option>
                <%
                    List<StudentInfo> studentNoRoom = (List<StudentInfo>)request.getAttribute("studentNoRoom");
                    if(studentNoRoom != null && studentNoRoom.size() != 0){
                        for (StudentInfo s : studentNoRoom) {%>
                <option value="<%=s.getId()%>"><%=s.getBase().getName()%></option>
                <%
                        }
                    }
                %>
            </select>
        </td>
        <td>
                <%
                if (studentNoRoom != null && studentNoRoom.size() != 0) {
                %>
            <input value="Add" type="submit">
                <%
                }
                %>
        </td>
    </tl>
</form>
</table>
</body>
</html>

