<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String userid = (String)request.getParameter("userid");
	String friendid = (String)request.getParameter("friendid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><a href="controlFlag.jsp?msg=9/<%=userid%>/<%=friendid%>/555555//">������ ����(�׳� "555555"���� �����ϵ����Ѵ�)</a></li>
		<li><a href="controlFlag.jsp?msg=1/<%=userid%>/<%=friendid%>/3//">�������(�׳� ��а� 3���� ����)</a></li>
		<li><a href="controlFlag.jsp?msg=2/<%=userid%>/<%=friendid%>//">��������</a></li>
		<li><a href="controlFlag.jsp?msg=0/<%=userid%>/<%=friendid%>//">�����޽�������</a></li>
		<li><a href="">��ư������� ����</a></li>
		<li><a href="controlFlag.jsp?msg=8/<%=userid%>/<%=friendid%>//">ģ������</a></li>
	</ul>
</body>
</html>