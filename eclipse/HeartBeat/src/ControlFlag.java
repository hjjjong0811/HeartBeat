import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import dao.FriendDAO;
import dao.MFDAO;
import dao.MemberDAO;
import dao.MsgDAO;
import dto.FriendDTO;
import dto.MFDTO;
import dto.MemberDTO;
import dto.MsgDTO;

public class ControlFlag {
	// Socket client;
	InputStream in;
	FriendDAO friendDAO = new FriendDAO();
	MsgDAO msgDAO = new MsgDAO();
	MemberDAO memberDAO = new MemberDAO();
	MFDAO mfDAO = new MFDAO();
	MemberDTO memberDTO = null;
	String[] value;
	int flag;

	public ControlFlag(String msg, InputStream in) {
		// System.out.println(msg);
		value = msg.split("/");
		flag = Integer.parseInt(value[0]);
		this.in = in;
	}

	public String doMsg() {
		String res = "false";
		switch (flag) {
		case 0:
		case 1:
		case 2:// 0_��������(���������ʹ� ���� ����, data�� �����̸� ��), 1_�������(data�� ��а���),
				// 2_��������
				// System.out.println("TEST!!!");
			if (friendDAO.checkFriend(value[1], value[2])) {
				// System.out.println("TEST!!!");
				MsgDTO msgDTO = new MsgDTO();
				msgDTO.setFlag(flag);
				msgDTO.setSender(value[1]);
				msgDTO.setReceiver(value[2]);
				// System.out.println("TEST!!!");
				if (flag == 0 || flag == 1) {
					msgDTO.setData(value[3]);
				} else {
					msgDTO.setData("0");
				}
				if (msgDAO.addMsg(msgDTO)) {
					res = "true";
				}
			}
			break;
		case 3:// 3_ģ���߰�
			if (!friendDAO.checkFriend(value[1], value[2])) {
				FriendDTO friendDTO = new FriendDTO();
				friendDTO.setFlag(false);
				friendDTO.setUserId(value[1]);
				friendDTO.setFriendId(value[2]);
				if (friendDAO.addFriend(friendDTO) == false)
					break;
				MsgDTO msgDTO = new MsgDTO();
				msgDTO.setFlag(flag);
				msgDTO.setSender(value[1]);
				msgDTO.setReceiver(value[2]);
				msgDTO.setData("0");
				if (msgDAO.addMsg(msgDTO))
					res = "true";
			}
			break;
		case 4:// �г��Ӽ���
			if (memberDAO.updateNick(value[2], value[1]))
				res = "true";
			break;
		case 5:// ��м���
			int mode = Integer.parseInt(value[2]);
			if (memberDAO.updateMode(mode, value[1]))
				res = "true";
			break;
		case 6:// ģ�����輺�� (6/RECEIVER/SENDER//)
			if (friendDAO.deleteFriend(value[1], value[2])) {
				//System.out.println("test1!!!!");
				FriendDTO friendDTO = new FriendDTO();
				friendDTO.setFlag(true);
				friendDTO.setFriendId(value[1]);
				friendDTO.setUserId(value[2]);
				if (friendDAO.addFriend(friendDTO)) {
					//System.out.println("test2!!!");
					friendDTO.setFriendId(value[2]);
					friendDTO.setUserId(value[1]);
					if (friendDAO.addFriend(friendDTO))
						res = "true";
					//System.out.println("test3!!! : " + res);
				}
			}
			break;
		case 7:// ģ����û ����
			if (!friendDAO.checkFriend(value[1], value[2])) {
				if (friendDAO.deleteFriend(value[1], value[2]))
					res = "true";
			}
			break;
		case 8:// ģ������
			if (friendDAO.deleteFriend(value[1], value[2]))
				res = "true";
			break;
		case 9:// ģ���� ���� ����
			if (friendDAO.friendColor(value[1], value[2], value[3]))
				res = "true";
			break;
		case 10:// ȸ������ ��ȿ�˻�
			// System.out.println("ȸ��������ȿ�˻� ���� : "+value[1]+"/"+value[2]);
			memberDTO = memberDAO.memberExist(value[1], value[2]);
			// System.out.println("ȸ��������ȿ�˻� ����");
			if (memberDTO != null) {
				res = memberDTO.getId() + "/" + memberDTO.getPwd() + "/" + memberDTO.getNick() + "/"
						+ memberDTO.getMmode() + "//";
			} // System.out.println("test~~"+res);
			break;
		case 11:// ID �����˻�
			if (memberDAO.idExist(value[1]))
				res = "true";
			break;
		case 12:// ȸ������ ����
			memberDTO = new MemberDTO();
			memberDTO.setId(value[1]);
			memberDTO.setPwd(value[2]);
			memberDTO.setNick(value[3]);
			if (memberDAO.join(memberDTO))
				res = "true";
			memberDTO = null;
			break;
		case 13:// ����� ģ���������
			HashMap<String, MFDTO> friends = mfDAO.listFriend(value[1]);
			if (friends.size() != 0 && friends != null) {
				res = "";
				Iterator it = friends.keySet().iterator();
				while (it.hasNext()) {
					String fid = (String) it.next();
					MFDTO dto = friends.get(fid);
					res += dto.getFriendid() + "/" + dto.getColor() + "/" + dto.getNick() + "/" + dto.getMode() + "/";
				}
			}
			break;
		case 14:// ��ȣȮ��_ �޼��� ���ſ�û
			MsgDTO msgDTO = msgDAO.getMsg(value[1]);
			if (msgDTO == null)
				res = "false";
			else if (msgDTO.getFlag() == 3 || friendDAO.checkFriend(msgDTO.getReceiver(), msgDTO.getSender())) {
				res = msgDTO.getFlag() + "/" + msgDTO.getSender() + "/" + msgDTO.getTime() + "/" + msgDTO.getData()
						+ "/";
				msgDAO.deleteMsg(msgDTO.getSender(), msgDTO.getTime());
			}
			break;
		}
		// System.out.println("test = "+res);
		return flag + "/" + res;
	}
}
