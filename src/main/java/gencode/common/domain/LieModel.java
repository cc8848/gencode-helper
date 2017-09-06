package gencode.common.domain;

import gencode.common.util.ColumnChangeTypeUtil;

public class LieModel {
	
	private String coltitle;//列名
	private String javatype;//java类型
	private String javaName;
	private String firstBigJavaName;
	private String dbtype;//数据库类型
	private int xsdcd;//小数点长度
	private String comments;//注释
	
	
	public String getFirstBigJavaName() {
		return firstBigJavaName;
	}
	public void setFirstBigJavaName(String firstBigJavaName) {
		this.firstBigJavaName = firstBigJavaName;
	}
	public String getJavaName() {
		return ColumnChangeTypeUtil.getJavaName(this.getColtitle());
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getColtitle() {
		return coltitle;
	}
	public void setColtitle(String coltitle) {
		this.coltitle = coltitle;
	}
	
	public String getJavatype() {
		return ColumnChangeTypeUtil.getJavaType(this.getDbtype(), this.getXsdcd());
	}
	public void setJavatype(String javatype) {
		this.javatype = javatype;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public int getXsdcd() {
		return xsdcd;
	}
	public void setXsdcd(int xsdcd) {
		this.xsdcd = xsdcd;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
