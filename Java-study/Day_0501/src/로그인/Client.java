package 로그인;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		DataOutputStream dos = null;
		DataInputStream dis = null;
		Socket client = null;
		
		try {
			client = new Socket("127.0.0.1", 10000);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			System.out.println("서버에 접속되었습니다.");

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패..");

		}

		while(true) {
			System.out.println("<< 회원 관리 시스템 >>");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.print(">> ");
			String menu = sc.nextLine();
			dos.writeUTF(menu);
			dos.flush();

			if(menu.equals("1")) {
				System.out.println("회원가입");
				System.out.print("ID : ");
				String id = sc.nextLine();
				dos.writeUTF(id);

				System.out.print("PW : ");
				String pw = sc.nextLine();
				dos.writeUTF(pw);

				System.out.print("Name : ");
				String name = sc.nextLine();
				dos.writeUTF(name);
				dos.flush();

				int result = dis.readInt(); // server에서 보낸 입력 성공 값 받기
				if(result > 0) {
					System.out.println("회원가입 성공");
				}

			} else if (menu.equals("2")) {
				System.out.println("로그인");
				System.out.print("ID : ");
				String id = sc.nextLine();
				dos.writeUTF(id);

				System.out.print("PW : ");
				String pw = sc.nextLine();
				dos.writeUTF(pw);
				dos.flush();

				boolean result = dis.readBoolean();

				if(result) {
					System.out.println("로그인 성공");
				} else {
					System.out.println("로그인 실패");
				}

			}


		}
	}
}
