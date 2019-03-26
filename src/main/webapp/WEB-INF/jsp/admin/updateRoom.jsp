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
<body>
<h1>UpdateRoom：<%=request.getAttribute("roomNum")%></h1>
<form>
    <table>
        <tl>
        <td>
            <a>StudentList:</a>
        </td>
    <%
        List<StudentInfo> studentList = (List<StudentInfo>)request.getAttribute("studentList");
        if(studentList != null && studentList.size() != 0){
            for(StudentInfo ss : studentList){
    %>
       <td>
           <a><%=ss.getBase().getName()%></a>
       </td>
        </tl>
    </table>
    <%
            }
        }else{
    %>
    <h2>No student in this room.</h2>
    <%}%>
</form>
<table>
<form action="/admin/updateRoomAct">
    <tl>
        <td>
            <select name="studentName" id=studentName>
                <option value="NoStudent"selected="selected">Select Student</option>
                <%
                    if(studentList != null && studentList.size() != 0){
                        for (StudentInfo s : studentList) {%>
                <option value="<%=s.getId()%>"><%=s.getBase().getName()%></option>
                <%
                        }
                    }
                %>
            </select>
        </td>
        <td>
            <input value="Remove" type="submit">
        </td>
    </tl>
</form>
<form action="/admin/updateRoomAct">
    <tl>

        <td>
            <input value="Add" type="submit">
        </td>
    </tl>
</form>
</table>
<td>
    <a href="index">index</a>
</td>
</body>
</html>

