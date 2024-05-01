import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		DataOutputStream dos = null;
		DataInputStream dis = null;

		// 192.168.1.15

		try {
			
			Socket client = new Socket("127.0.0.1", 10000);
//			Socket client = new Socket("192.168.1.32", 10000);
//			Socket client = new Socket("192.168.1.19", 30000);
			System.out.println("서버에 접속되었습니다.");
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패...");
		}

		while (true) {
			System.out.println("<< 회원 인증 시스템 >>");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			String menu = sc.nextLine();

			// 1' or 1=1--
			if (menu.equals("1")) {
				dos.writeUTF(menu);
				dos.flush();

				System.out.println("정보를 입력하세요.");
				System.out.print("ID : ");
				String id = sc.nextLine();

				System.out.print("PW : ");
				String pw = sc.nextLine();

				dos.writeUTF(id);
				dos.writeUTF(pw);
				dos.flush();

				boolean result = dis.readBoolean();
				if (result) {
					System.out.println("로그인 성공");
				} else {
					System.out.println("로그인 실패");
				}

//				String msg = dis.readUTF();
//				System.out.println(msg);
//
//				if (msg.equals("로그인 실패")) {
//					continue;
//				}

			} else if (menu.equals("2")) {
				dos.writeUTF(menu);
				dos.flush();

				System.out.println("정보를 입력하세요.");
				System.out.print("ID : ");
				String id = sc.nextLine();

				System.out.print("PW : ");
				String pw = sc.nextLine();

				System.out.print("Name : ");
				String name = sc.nextLine();

				dos.writeUTF(id);
				dos.writeUTF(pw);
				dos.writeUTF(name);
				dos.flush();

				int result = dis.readInt();
				if (result > 0) {
					System.out.println("회원가입 성공");
				}

			} else {
				System.out.println("재입력하세요.");
			}

		}
	}
}
