<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
�α������ּ���-> �α��� ����ȭ�� ���� ��
<form method="post" action="loginPro.jsp">
	���̵� : <input type="text" name="id"><br>
	��й�ȣ : <input type="text" name="pwd"><br>
	<input type="submit" value="�α���">
</form>
<hr>
���̵� �ߺ�Ȯ��
<form method="post" action="controlFlag.jsp">
	���̵� : h(�ִ¾��̵�)
	<input type="hidden" name="msg" value="11/h//">
	<input type="submit" value="�ߺ�Ȯ��">
</form>
<form method="post" action="controlFlag.jsp">
	���̵� : hasdf(���¾��̵�)
	<input type="hidden" name="msg" value="11/hasdf//">
	<input type="submit" value="�ߺ�Ȯ��">
</form>
<hr>
ȸ������
<form method="post" action="controlFlag.jsp">
	[�׽�Ʈ��] ���̵� : hi / ��� : hi / �г��� : hi<br>
	<input type="hidden" name="msg" value="12/hi/hi/hi//">
	<input type="submit" value="ȸ������">
</form>
<hr>
�α���ó�� flagȮ�ο�_ test
<form method="post" action="controlFlag.jsp">
	���̵� : hi / ��� : hi<br>
	<input type="hidden" name="msg" value="10/hi/hi//">
	<input type="submit" value="ȸ������ ����Ȯ��">
</form>
</body>
</html>