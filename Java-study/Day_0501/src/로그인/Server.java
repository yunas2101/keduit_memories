package 로그인;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

public class Server {

	/**
	 * 암호화 메서드
	 * @param input
	 * @return
	 */
	public static String getSHA512(String input) {
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(10000);

		Socket socket = server.accept();
		System.out.println(socket.getInetAddress() + " 로부터 접속");

		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(socket.getInputStream());

		ServerDAO dao = new ServerDAO();

		
		// while문이 여기에 있어야 함.  ServerSocket 위에 가있으면 회원가입, 로그인 끝내고 새로운 사용자를 받으려고 대기중인 상태
		// 근데 Client 쪽에서는 계속 입력을 원하고 있어서 실행순서 맞지 않음
		// 에러는 안뜨지만 입력하는 창이 안나오게 됨.
		while(true) { 

			String menu = dis.readUTF(); // client로부터 메뉴 입력값 받기


			/** 회원가입 **/
			if(menu.equals("1")) { 
				String id = dis.readUTF();
				String pw = getSHA512(dis.readUTF());
				String name = dis.readUTF();

				ServerDTO dto = new ServerDTO(id, pw, name);
				int msg = dao.joinMembers(dto); // int result = pstat.executeUpdate();를 받는 메서드 

				dos.writeInt(msg); // 결과값을 Client로 보내
				dos.flush();

				/** 로그인 **/
			} else if(menu.equals("2")) {
				String id = dis.readUTF();
				String pw = getSHA512(dis.readUTF());

				boolean result = dao.addMembers(id, pw);

				dos.writeBoolean(result);
				dos.flush();
			}

		}

	}

}
