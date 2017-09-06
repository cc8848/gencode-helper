package ${packpath!}service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import ${packpath}model.${className};
import ${packpath!}dao.${className}Dao;
<#if methodStr?index_of("searchPager_")!=-1>
import com.github.pagehelper.PageHelper;
import com.weds.framework.core.common.model.CommPager;
</#if>
/**
 * 对${className}实体进行CRUD操作<#assign a=id?split("@")/>
 */
@Service
public class ${className}Service {
	
	@Autowired
	private ${className}Dao ${className?uncap_first}Dao;
<#if methodStr?index_of("save_")!=-1>

	public void save(${className} ${className?uncap_first}) {
		${className?uncap_first}Dao.save(${className?uncap_first});
	}
</#if><#if methodStr?index_of("deleteById_")!=-1>

	public void deleteBy${a[0]?cap_first}(${a[1]} ${a[0]}) {
		${className?uncap_first}Dao.deleteById(${a[0]});
	}
</#if><#if methodStr?index_of("update_")!=-1>

	public void update(${className} ${className?uncap_first}) {
		${className?uncap_first}Dao.update(${className?uncap_first});
	}
</#if><#if methodStr?index_of("getById")!=-1>

	public ${className} getBy${a[0]?cap_first}(${a[1]} ${a[0]}) {
		return ${className?uncap_first}Dao.getById(${a[0]});
	}
</#if><#if methodStr?index_of("getByModel_")!=-1>

	public ${className} getByModel(${className} ${className?uncap_first}) {
		return ${className?uncap_first}Dao.getByModel(${className?uncap_first});
	}
</#if><#if methodStr?index_of("searchList_")!=-1>

	public List<${className}> searchList(${className} ${className?uncap_first}) {
		return ${className?uncap_first}Dao.searchList(${className?uncap_first});
	}
</#if><#if methodStr?index_of("searchPager_")!=-1>
	public CommPager searchPager(${className} ${className?uncap_first},int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List l = ${className?uncap_first}Dao.searchList(${className?uncap_first});
		return new CommPager(l);
	}
</#if>
}
