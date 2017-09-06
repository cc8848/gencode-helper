package gencode.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gencode.common.BeanService;
import gencode.common.domain.BeanModel;
import gencode.common.domain.LieModel;

public class MysqlServiceImpl implements BeanService {

	@Override
	public BeanModel getBeanModel(String driver, String url, String username, String pwd, String tname,String scheme) {
		BeanModel bm = new BeanModel();
		bm.setTableName(tname);
		List<LieModel> lm = new ArrayList<LieModel>();
		Connection conn = null;
		try {
			Class.forName(driver);
			// 测试的链接
			conn = DriverManager.getConnection(url, username, pwd);
			String sql = "select t.column_name,t.data_type,t.numeric_scale DATA_SCALE,t.column_comment COMMENTS "
					+ "from information_schema.columns t where table_schema ='"+scheme+"'  and table_name = '"+tname+"'"; 
			Statement st = null;
			LieModel m = null;
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				m = new LieModel();
				m.setColtitle(rs.getString("COLUMN_NAME").toLowerCase());// 获取字段名
				m.setDbtype(rs.getString("DATA_TYPE").toLowerCase());// 获取数据类型
				m.setXsdcd((null==rs.getString("DATA_SCALE"))?0:Integer.valueOf(String.valueOf(rs.getString("DATA_SCALE"))));
				m.setComments(StringUtils.isBlank(rs.getString("COMMENTS"))?"":rs.getString("COMMENTS"));
				lm.add(m);
			}
			bm.setLiemodel(lm);
			bm.setDbtype("mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}

}
