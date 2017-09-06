package gencode.sqlserver;

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

public class SQLServerServiceImpl implements BeanService {

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
			String sql = "SELECT col.name AS column_name, t.name AS data_type,"
					+ "ISNULL(cast(ep.[value] as varchar(500)), '') AS COMMENTS,"
					+ "ISNULL(COLUMNPROPERTY(col.id, col.name, 'Scale'), 0) AS DATA_SCALE "+
			"FROM   dbo.syscolumns col  "
			+ "LEFT  JOIN dbo.systypes t ON col.xtype = t.xusertype  "
			+ "inner JOIN dbo.sysobjects obj ON col.id = obj.id  AND obj.xtype = 'U'  AND obj.status >= 0   "
			+ "LEFT  JOIN sys.extended_properties ep ON col.id = ep.major_id  AND col.colid = ep.minor_id  AND ep.name = 'MS_Description' "
			+ " WHERE   obj.name = '"+tname+"'"; 
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
