package ${packpath!}model;
import java.io.Serializable;
/**
* 实体类：${className!}
* 对照表：${tableName!}
*/
public class ${className!} implements Serializable {
	<#list liemodel as col>
  	//${col.comments}
	private ${col.javatype} ${col.javaName};
	</#list>
	
	//-----------模糊查询属性-------------//
	<#list liemodel as col>
		<#list likeCols as llike>
		  	<#if (col.coltitle?upper_case == llike?upper_case && col.javatype?lower_case?contains("string"))>
	private String ${col.javaName}Like;
		  	</#if>
		</#list>
	</#list>
	<#list liemodel as col>
		<#list likeCols as llike>
		  	<#if col.coltitle?upper_case == llike?upper_case && col.javatype?lower_case?contains("string")>
	public String get${col.javaName?cap_first}Like() {
		return ${col.javaName}Like;
	}
	public void set${col.javaName?cap_first}Like(${col.javatype} ${col.javaName}Like) {
		this.${col.javaName}Like = ${col.javaName}Like;
	}
		  	</#if>
		</#list>
	</#list>
	//-------------get,set--------------//
	<#list liemodel as col>
	/**
	  * ${col.comments}
	  */
	public ${col.javatype} get${col.javaName?cap_first}() {
		return ${col.javaName};
	}
	/**
	  * ${col.comments}
	  */
	public void set${col.javaName?cap_first}(${col.javatype} ${col.javaName}) {
		this.${col.javaName} = ${col.javaName};
	}
	</#list>
}