<%@page import="java.util.Iterator"%>
<%@page import="dto.MFDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.MFDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String id = (String) request.getParameter("id");
	MFDAO mfDAO = new MFDAO();
	HashMap<String, MFDTO> friend = mfDAO.listFriend(id);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3> Welcome!! <%=id%></h3>
	!!���λ�!!
	<ul>
		<li><a href="">�޽�������Ȯ��</a></li>
		<li><a href="controlFlag.jsp?msg=5/<%=id%>/6//">��м���(�׳� 6�� ���� �����ϱ��)</a></li>
	</ul>
	<hr color="orange">
	<ul>
		<li><a href="">�޽������ſ�û</a></li>
		<li><a href="controlFlag.jsp?msg=4/<%=id%>/��ü��//">�г��Ӽ���(�׳� ��ü������ ��)</a></li>
		<li><a href="controlFlag.jsp?msg=3/<%=id%>/j//">ģ����û</a></li>
	</ul>
	<hr color="orange">
	<h2> ģ���� : <%=friend.size()%>�� </h2>

	<ul>
		<%
			if (friend.size() != 0 && friend != null) {
				Iterator it = friend.keySet().iterator();
				while (it.hasNext()) {
					String fid = (String) it.next();
					MFDTO dto = friend.get(fid);
		%>
		<li><a href="friend_detail.jsp?userid=<%=id%>&friendid=<%=fid%>"><%=dto.getFriendid()%> / <%=dto.getNick()%> / <%=dto.getMode()%> / <%=dto.getColor()%></a></li>

		<% } } %>
	</ul>
	<hr color="orange">
</body>
</html>