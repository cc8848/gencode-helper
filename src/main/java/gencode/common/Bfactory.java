package gencode.common;

import gencode.mysql.MysqlServiceImpl;
import gencode.oracle.OracleServiceImpl;
import gencode.sqlserver.SQLServerServiceImpl;

import org.apache.commons.lang3.StringUtils;

public class Bfactory {

	public static BeanService getBservice(String dbtype) {
		if(StringUtils.equals("mysql", dbtype)){
			return (BeanService) new MysqlServiceImpl();
		}else if(StringUtils.equals("oracle", dbtype)){
			return (BeanService) new OracleServiceImpl();
		}
		else if(StringUtils.equals("sqlserver", dbtype)){
			return (BeanService) new SQLServerServiceImpl();
		}
		return null;
	}
}
