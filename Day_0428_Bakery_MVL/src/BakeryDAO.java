
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


public class BakeryDAO {

	/** 배열에 저장하는 용도 **/
	ArrayList<BakeryDTO> bakeryList = new ArrayList<BakeryDTO>();

	/** DB에 연결 **/
	public Connection dbCon() throws Exception {
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kedu";
		String dbPW = "kedu";

		Connection con = DriverManager.getConnection(dbURL, dbID, dbPW);

		return con;
	}

	/** 사용자에게 받은 값을 "DB"에 저장 **/
	public void addBakery(BakeryDTO dto) throws Exception {

		Connection con = dbCon();
		Statement stat = con.createStatement();
		String sql = "insert into bakery values(cafe_seq.nextval, '" + dto.getName() + "' , dto.getPrice() ) ";
		int result = stat.executeUpdate(sql);

		con.close();
	}

//	/** 사용자에게 받은 값 저장 **/
//	public void addBakery(BakeryDTO dto) {
//		bakeryList.add(dto);
//	}

	/** 목록에 있는 빵 리스트 보여주기 **/
	public ArrayList<BakeryDTO> getBakerys() {
		return bakeryList;
	}

	/** 사용자에게 name 받고, 목록에 이름 포함된게 있다면, 출력 **/
	public ArrayList<BakeryDTO> searchBakery(String name) {
		ArrayList<BakeryDTO> result = new ArrayList<BakeryDTO>();

		for (BakeryDTO b : bakeryList) {
			if (b.getName().contains(name)) {
				result.add(b);
			}
		}

		return result;
	}

	/** 사용자에게 받은 id와 동일한 id 있는지 확인 후, 삭제 **/
	public void deleteBakery(int id) {
		for (BakeryDTO b : bakeryList) {
			if (b.getId() == id) {
				bakeryList.remove(b);
				break;
			}
		}
	}

	/** 수정할 빵의 id 받고, 동일한 id 있다면 이름&가격 수정 **/
	public void updateBakery(int id, String name, int price) {
		for (BakeryDTO b : bakeryList) {
			if (b.getId() == id) {
				b.setName(name);
				b.setPrice(price);
				break;
			}
		}

	}
}
