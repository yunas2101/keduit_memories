package main;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BoardDAO;
import dao.MembersDAO;
import dto.BoardDTO;
import dto.MembersDTO;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MembersDAO mDao = new MembersDAO();
		BoardDAO bDao = new BoardDAO();
		MembersDTO dto = new MembersDTO();

		while (true) {
			try {
				System.out.println("<< Mini Board 인증 >>");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.print(">> ");
				String menu = sc.nextLine();

				if (menu.equals("1")) {
					System.out.println("로그인 정보 입력하세요.");
					System.out.print("ID : ");
					String id = sc.nextLine();

					System.out.print("PW : ");
					String pw = sc.nextLine();

					boolean result = mDao.loginMember(id, pw);
					if (result) {
						System.out.println("로그인 성공!");

						break;
					}

				} else if (menu.equals("2")) {
					System.out.println("회원가입 정보 입력하세요.");
					System.out.print("ID : ");
					String id = sc.nextLine();

					System.out.print("PW : ");
					String pw = sc.nextLine();

					System.out.print("이름 : ");
					String name = sc.nextLine();

					dto = new MembersDTO(id, pw, name, null);
					int result = mDao.joinMember(dto);

					if (result > 0) {
						System.out.println("입력 성공!");
					}

				} else {
					System.out.println("재입력하세요.");

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("관리자에게 문의하세요. admin@site.com");
			}

		}

		while (true) {
			try {
				System.out.println("<< Mini Board >>");
				System.out.println("1. 글 작성하기");
				System.out.println("2. 글 목록 보기");
				System.out.println("3. 글 검색 하기");
				System.out.println("4. 글 수정 하기");
				System.out.println("5. 글 삭제 하기");
				System.out.println("0. 시스템 종료");
				System.out.print(">> ");
				String menu = sc.nextLine();

				if (menu.equals("1")) {
					System.out.println("== 글 작성하기 ==");
					String writer = dto.getName();
					String contents = sc.nextLine();

					int result = bDao.addBoard(writer, contents);
					if (result > 0) {
						System.out.println("작성되었습니다.");
					}

				} else if (menu.equals("2")) {
					System.out.println("번호\t작성자\t내용\t\t\t작성날짜");

					ArrayList<BoardDTO> boardList = bDao.selectList();
					if (boardList.size() == 0) {
						System.out.println("작성된 글이 없습니다.");
					} else {
						for (BoardDTO bDto : boardList) {
							System.out.println(bDto.getSeq() + "\t" + bDto.getWriter() + "\t" + bDto.getContents()
									+ "\t\t" + bDto.getFormatDate());
						}
					}

				} else if (menu.equals("3")) {
					System.out.print("검색할 내용 및 작성자 : ");
					String inputSearch = sc.nextLine();

					ArrayList<BoardDTO> searchList = bDao.searchBoard(inputSearch);
					if (searchList.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						for (BoardDTO bDto : searchList) {
							System.out.println(bDto.getSeq() + "\t" + bDto.getWriter() + "\t" + bDto.getContents()
									+ "\t" + bDto.getWrite_date());
						}
					}

				} else if (menu.equals("4")) {
					System.out.print("수정할 글의 글번호 : ");
					int seq = Integer.parseInt(sc.nextLine());

					boolean result = bDao.isSeqExist(seq);
					if (result) {
						System.out.print("수정할 작성자 이름 : ");
						String writer = sc.nextLine();

						System.out.print("수정할 글 내용 : ");
						String contents = sc.nextLine();

						System.out.print("수정할 날짜 (yyyy/MM/dd) : ");
						String write_date = sc.nextLine();

						int results = bDao.updateBoard(seq, writer, contents, write_date);
						if (results > 0) {
							System.out.println("수정되었습니다.");
						}

					}

				} else if (menu.equals("5")) {
					System.out.print("삭제할 글의 번호 : ");
					int seq = Integer.parseInt(sc.nextLine());

					int result = bDao.deleteBoard(seq);
					if (result > 0) {
						System.out.println("삭제되었습니다.");
					}

				} else if (menu.equals("0")) {
					System.out.println("종료합니다.");
					System.exit(0);
				} else {
					System.out.println("재입력하세요.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("관리자에게 문의해주세요. admin@site.com");

			}
		}

	}
}
