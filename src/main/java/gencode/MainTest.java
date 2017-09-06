package gencode;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import gencode.common.BeanService;
import gencode.common.Bfactory;
import gencode.common.domain.BeanModel;
import gencode.common.util.GenCodeUtil;

public class MainTest {

	public static final String qianbian = "com.test";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// oracle oracle oracle
		// String dbtype = "oracle";
		// String driver = "oracle.jdbc.driver.OracleDriver";
		// String url = "jdbc:oracle:thin:@127.0.0.1:1521/XE";
		// String username = "test";
		// String pwd = "test";
		//
		System.err.print("输入3才能生成生成代码，确定生成请输入:");
		Scanner s = new Scanner(System.in);
		String a = s.next();
		if (!StringUtils.equals(a, "3")) {
			System.err.print("输入不是3，结束！");
			return;
		}
		// mysql mysql mysql
		String dbtype = "mysql";// mysql
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.27.4.55:3306/sharedata";
		String username = "root";
		String pwd = "wdoor";
		String scheme = "sharedata";
		// 测试的链接
		// ===========================================================================
		// 要生成的表
		String tname = "st_device";
		// ============================================================================

		BeanService bs = Bfactory.getBservice(dbtype);
		BeanModel bm = bs.getBeanModel(driver, url, username, pwd, tname,
				scheme);
		// 基本路径
		String clspath = "com\\t\\manage\\";
		bm.setClspath(clspath);

		// 需要模糊匹配的字段
		String[] likeCols = { "code", "name", "des" };
		bm.setLikeCols(likeCols);

		// 需要set in 查询的字段
		String[] inCol = { "", "" };
		// 表主键,空格后面跟的是这个字段的数据类型
		String id = "id@Long";
		bm.setId(id);
		// 可以生成的方法
		String methodStr = "" + "save_" // 增加
				+ "deleteById_" // 根据主键删除
				+ "update_" // 修改
				+ "getById_" // 根据主键得到
				+ "getByModel_" // 根据其他字段得到
				+ "searchList_" // 查询list
				+ "searchPager_" // 查询分页
				+ "";
		bm.setMethodStr(methodStr);
		// 可以生成的类型
		String typeflag = "" + "domain_" + "dao_" + "mapper_" + "service_"
				+ "serviceimpl_" + "action_" + "jsp_"
				// + "jspaddupate_"
				+ "";
		bm.setTypeflag(typeflag);

		// 生成代码
		GenCodeUtil.genCode(bm);

	}
}
