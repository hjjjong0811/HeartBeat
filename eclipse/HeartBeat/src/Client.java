import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {
	private Socket client;

	public Client(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		InputStream in = null;
		OutputStream out=null;
		try {
			// ��Ʈ��ũ���~
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			byte buf[] = new byte[512];
			// ����
			in = client.getInputStream();
			in.read(buf);
			String msg = new String(buf).trim();
			System.out.println("[���� ������] " + msg);
			// ������ó��
			ControlFlag ctrlF = new ControlFlag(msg);
			String res = ctrlF.doMsg();
			// �۽�
			out = client.getOutputStream();
			out.write(res.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				in.close();
				out.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
