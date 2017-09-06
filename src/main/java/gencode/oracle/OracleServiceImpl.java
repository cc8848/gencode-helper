package gencode.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import gencode.common.BeanService;
import gencode.common.domain.BeanModel;
import gencode.common.domain.LieModel;

public class OracleServiceImpl implements BeanService{

	@Override
	public BeanModel getBeanModel(String driver, String url, String username, String pwd,String tname,String scheme) {
		BeanModel bm = new BeanModel();
		bm.setTableName(tname);
		List<LieModel> lm = new ArrayList<LieModel>();
		Connection conn;
		try {
			Class.forName(driver);
//			测试的链接
			conn = DriverManager.getConnection(url,username,pwd);
			
			String sql = "select a.COLUMN_NAME, a.DATA_TYPE, a.DATA_SCALE, "+
					       " a.NULLABLE,"+
					       " b.COMMENTS　from user_tab_columns a,"+
					       " user_col_comments b"+
					       " where a.table_name = b.table_name"+
					       " and a.column_name = b.column_name"+
					       " and a.table_name = UPPER('" + tname + "')";
			Statement st = null;
			LieModel m = null;
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				m = new LieModel();
				m.setColtitle(rs.getString("COLUMN_NAME").toLowerCase());// 获取字段名
				m.setDbtype(rs.getString("DATA_TYPE").toLowerCase());// 获取数据类型
				m.setXsdcd((null==rs.getString("DATA_SCALE"))?0:Integer.valueOf(String.valueOf(rs.getString("DATA_SCALE"))));
				m.setComments(rs.getString("COMMENTS")==null?"":rs.getString("COMMENTS"));
				lm.add(m);
			}
			bm.setLiemodel(lm);
			bm.setDbtype("oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}

}
