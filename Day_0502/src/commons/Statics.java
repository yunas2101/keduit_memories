package commons;

import org.apache.commons.dbcp2.BasicDataSource;

public class Statics {
	public static String name = "name";
	public static BasicDataSource bds = new BasicDataSource();
	static {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kedu");
		bds.setPassword("kedu");
		bds.setInitialSize(50);
	}

}
