package ${packpath!}dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.weds.framework.core.annotation.MyBatisDao;
import ${packpath!}model.${className!};

/**
 * 对${className}实体进行CRUD操作
 */
@MyBatisDao
public interface ${className}Dao{

	<#if methodStr?index_of("save")!=-1>
	//----------增加	
	/**
	 * @param ${className}
	 * 添加
	 */
	public void save(@Param("model")${className} ${className?uncap_first});
	</#if><#if methodStr?index_of("deleteById_")!=-1>
	//-------根据主键删除
	/**
	 * @param ${className}
	 * 删除
	 */
	public void deleteById(<#assign a=id?split("@")/>@Param("${a[0]}")${a[1]} ${a[0]});
	</#if><#if methodStr?index_of("update_")!=-1>
	//---------------全字段修改
	/**
	 * @param ${className}
	 * 修改
	 */
	public void update(@Param("model")${className} ${className?uncap_first});
	</#if>
	<#if methodStr?index_of("getById")!=-1>
	//---------------查找
	/**
	 * @param 
	 * @return 查找
	 */
	public ${className} getById(<#assign a=id?split("@")/>@Param("${a[0]}")${a[1]} ${a[0]});
	</#if>
	<#if methodStr?index_of("getByModel_")!=-1>
	/**
	 * @param 
	 * @return 全部字段查找
	 */
	public ${className} getByModel(@Param("model")${className} ${className?uncap_first});
	</#if>
	<#if methodStr?index_of("searchList_")!=-1>
	//-------------查找list
	/**
	 * @param model
	 */
	public List<${className}> searchList(@Param("model")${className} ${className?uncap_first});
	</#if>
}
