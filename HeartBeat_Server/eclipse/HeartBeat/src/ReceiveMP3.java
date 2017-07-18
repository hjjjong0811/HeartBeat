import java.net.*;
import java.io.*;

public class ReceiveMP3 {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(12000);
			System.out.println("ReceiveMP3 Server ready..");
			while (true) {
				Socket s = ss.accept();
				System.out.println("���� ����");
				System.out.println(s.getInetAddress());// Ip address
				ReceiveServerThread st = new ReceiveServerThread(s);
				st.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}// end of class

class ReceiveServerThread extends Thread {
	Socket socket;

	public ReceiveServerThread(Socket s) {
		socket = s;
	}

	public void run() {
		try {
			// client�ܿ��� ���۵� file���� read �迭 stream
			BufferedInputStream up = new BufferedInputStream(socket.getInputStream());
			DataInputStream fromClient = new DataInputStream(up);
			// ���۵� file�� reading
			System.out.println("���ϸ� �ޱ� �����...");
			String filename = fromClient.readUTF();
			System.out.println(filename + "\t�� �޽��ϴ�.");

			// client�ܿ��� ���۵Ǵ� file ������ server�ܿ� ������Ų file�� write�Ҽ� �ִ� stream
			FileOutputStream toFile = new FileOutputStream("C:\\Users\\Hobin\\Desktop\\Server\\" + filename);
			BufferedOutputStream outFile = new BufferedOutputStream(toFile);
			int ch = 0;
			while ((ch = up.read()) != -1) {
				System.out.println(ch);//test
				outFile.write(ch);
			}
			outFile.flush();
			outFile.close();
			fromClient.close();
			socket.close();
			System.out.println("Ŭ���̾�Ʈ ����\n");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
