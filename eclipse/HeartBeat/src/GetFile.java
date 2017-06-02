import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class GetFile {
	private String path = "C:\\Users\\Hobin\\Desktop\\Capstone\\Server_File\\";
	// private Socket sock;
	private InputStream ins;
	BufferedReader in;
	FileOutputStream output;

	public GetFile(InputStream in) {
		this.ins = in;
	}

	public boolean getFile(String sender, String receiver) {
		try {
			// 1. �����̸��ޱ�
			in = new BufferedReader(new InputStreamReader(ins));
			String str = in.readLine();
			System.out.println("�������� ���� : " + str);
			File f = new File(path, sender + "_" + receiver + "_" + str);
			output = new FileOutputStream(f);
			byte[] buf = new byte[1024];////////////

			// 2. ���Ϲޱ�
			int readBytes = 0;
			ins.reset();
			//ins.available();
			while ((readBytes = ins.read(buf))>0) {
				System.out.println("���ϼ�����...");
				output.write(buf, 0, readBytes);
				System.out.println(readBytes);
			}if(readBytes==0) return false;
			System.out.println(str + " �������� ���ſϷ�");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
