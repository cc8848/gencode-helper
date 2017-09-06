package ${packpath!}service;

import java.util.List;
import com.weds.framework.core.common.model.CommPager;
import ${packpath!}api.model.${className};
/**
 * 对${className}实体进行CRUD操作
 * 表：${tableName}<#assign a=id?split("@")/>
 */
public interface I${className}Service{
<#if methodStr?index_of("save_")!=-1>
	//----------增加	
	/**
	 *  ${className?uncap_first}
	 * 添加
	 */
	public void save(${className} ${className?uncap_first});	
</#if><#if methodStr?index_of("deleteById_")!=-1>
	//-------根据主键删除
	/**
	 *  ${className?uncap_first}
	 * 删除
	 */
	public void deleteBy${a[0]?cap_first}(${a[1]} ${a[0]});
</#if><#if methodStr?index_of("update_")!=-1>
	//---------------全字段修改
	/**
	 *  ${className?lower_case}
	 * 修改
	 */
	public void update(${className} ${className?uncap_first});
</#if><#if methodStr?index_of("getById")!=-1>
	//---------------查找
	/**
	 *  
	 * @return 查找
	 */
	public ${className} getBy${a[0]?cap_first}(<#assign a=id?split("@")/>${a[1]} ${a[0]});
</#if><#if methodStr?index_of("getByModel_")!=-1>
	/**
	 *  
	 * @return 全部字段查找
	 */
	public ${className} getByModel(${className} ${className?uncap_first});
</#if><#if methodStr?index_of("searchList_")!=-1>
	//-------------查找list
	/**
	 *  model
	 */
	public List<${className}> searchList(${className} ${className?uncap_first});
</#if><#if methodStr?index_of("searchPager_")!=-1>
	/**
	 *  ${className?lower_case}
	 * @return
	 * 分页查询
	 */
	public CommPager searchPager(${className} ${className?uncap_first},int pageNum,int pageSize);
</#if>
}
