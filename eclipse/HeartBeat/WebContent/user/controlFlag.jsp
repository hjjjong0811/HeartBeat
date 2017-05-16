<%@page import="dto.MemberDTO"%>
<%@page import="dto.FriendDTO"%>
<%@page import="dao.MemberDAO"%>
<%@page import="dao.MsgDAO"%>
<%@page import="dto.MsgDTO"%>
<%@page import="dao.FriendDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String msg = (String) request.getParameter("msg");
	String[] value = msg.split("/");
	int flag = Integer.parseInt(value[0]);
	FriendDAO friendDAO = new FriendDAO();
	MsgDAO msgDAO = new MsgDAO();
	MemberDAO memberDAO = new MemberDAO();
	boolean res = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		switch (flag) {
		case 0:case 1:case 2://0_��������(���������ʹ� ���� ����), 1_�������(data�� ��а���), 2_��������
			if (friendDAO.checkFriend(value[1], value[2])) {
				MsgDTO msgDTO = new MsgDTO();
				msgDTO.setFlag(flag);
				msgDTO.setSender(value[1]);
				msgDTO.setReceiver(value[2]);
				if(flag==1)msgDTO.setData(Integer.parseInt(value[3]));
				res = msgDAO.addMsg(msgDTO);
			} 
			break;
		case 3://3_ģ���߰�
			if(!friendDAO.checkFriend(value[1], value[2])){
				FriendDTO dto = new FriendDTO();
				dto.setFlag(false);
				dto.setUserId(value[1]);
				dto.setFriendId(value[2]);
				if(friendDAO.addFriend(dto)==false) break;
				MsgDTO msgDTO = new MsgDTO();
				msgDTO.setFlag(flag);
				msgDTO.setSender(value[1]);
				msgDTO.setReceiver(value[2]);
				if(flag==1)msgDTO.setData(Integer.parseInt(value[3]));
				res = msgDAO.addMsg(msgDTO);
			}
			break;
		case 4://�г��Ӽ���
			res = memberDAO.updateNick(value[2], value[1]);
			break;
		case 5://��м���
			int mode = Integer.parseInt(value[2]);
			res = memberDAO.updateMode(mode, value[1]);
			break;
		case 6://ģ����û ����
			break;
		case 7://ģ����û ����
			break;
		case 8://ģ������
			res=friendDAO.deleteFriend(value[1], value[2]);
			break;
		case 9://ģ��������
			if (friendDAO.checkFriend(value[1], value[2])) {
				res = friendDAO.friendColor(value[1], value[2], value[3]);
			}
			break;
		case 10://ȸ������ ��ȿ�˻�
			res = memberDAO.memberExist(value[1], value[2]);
			break;
		case 11://���̵� �ߺ�Ȯ��
			res = memberDAO.idExist(value[1]);
			break;
		case 12://ȸ������
			MemberDTO dto = new MemberDTO();
			dto.setId(value[1]);
			dto.setPwd(value[2]);
			dto.setNick(value[3]);
			res = memberDAO.join(dto);
			break;
		case 13://����� ģ���������
			break;
		case 14://�޼��� ���ſ�û
			
			break;
		}
	%>
	flag :
	<%=value[0]%>
	/ �������� :
	<%=res%>
</body>
</html>