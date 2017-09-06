package gencode.common.domain;

import java.util.List;

import gencode.common.util.ColumnChangeTypeUtil;

public class BeanModel {
	private String dbtype;
	
//	表名
	private String tableName;
//	类名
	private String className;
//	列信息
	private List<LieModel> liemodel;
	
//	表主键
	public String id;
//	模糊查询的字段
	public String[] likeCols;
//	可生成的方法
	public String methodStr;
//	可生成的类型
	public String typeflag;
	
//	基本路径
	public String clspath;
//	包路径
	public String packpath;
	
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getPackpath() {
		return packpath;
	}
	public void setPackpath(String packpath) {
		this.packpath = packpath;
	}
	public String getClspath() {
		return clspath;
	}
	public void setClspath(String clspath) {
		this.clspath = clspath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getLikeCols() {
		return likeCols;
	}
	public void setLikeCols(String[] likeCols) {
		this.likeCols = likeCols;
	}
	public String getMethodStr() {
		return methodStr;
	}
	public void setMethodStr(String methodStr) {
		this.methodStr = methodStr;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	public String getClassName() {
		return ColumnChangeTypeUtil.getClsName(tableName);
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<LieModel> getLiemodel() {
		return liemodel;
	}
	public void setLiemodel(List<LieModel> liemodel) {
		this.liemodel = liemodel;
	}
	 
	
}
