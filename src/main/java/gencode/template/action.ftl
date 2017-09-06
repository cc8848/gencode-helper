package com.platform.web.${oneCeng};

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ${packageStr}.domain.${leiName};
import com.platform.basic.service.${leiName}Service;
import com.platform.common.util.SystemException;
import com.platform.web.BasicBaseAction;

public class ${leiName}Action extends BasicBaseAction {
	
	@Autowired
	protected ${leiName}Service ${fuperLeiName}Service;

	private ${leiName} model = new ${leiName}();
	
	
	/**
	 * @return
	 * 分页查询
	 */
	public String main() {
		return SUCCESS;
	}
	
	public String toaddupdate() {
		model = ${fuperLeiName}Service.getById(model.getId());
		return "toaddupdate";
	}
	/**
	 * @return
	 * 分页查询
	 */
	public String searchPager() {
		pageInfo = ${fuperLeiName}Service.searchPager(model);
		return EASYUIPAGER;
	}
	
	/**
	 * @return
	 * 新建
	 */
	public String saveOrupdate(){
		try{
			if(null!=model.getId()){
				model.setLdate(Calendar.getInstance().getTime());
				model.setLuserid(getCurrentUserInfo().getId());
				model.setLusername(getCurrentUserInfo().getName());
				${fuperLeiName}Service.update(model);
			}else{
				model.setCdate(Calendar.getInstance().getTime());
				model.setCuserid(getCurrentUserInfo().getId());
				model.setCusername(getCurrentUserInfo().getName());
				${fuperLeiName}Service.save(model);
			}
		}catch(Exception e){
			this.getJsonResult().setFlag(false);
			this.getJsonResult().setMsg("系统提示信息："+e.getMessage());
		}
		return JSONRESULT;
	}
	/**
	 * @return
	 * 根据主键删除
	 */
	public String delete(){
		try{
			${fuperLeiName}Service.deleteById(model.getId());
		}catch(Exception e){
			this.getJsonResult().setFlag(false);
			this.getJsonResult().setMsg("系统提示信息："+e.getMessage());
		}
		return JSONRESULT;
	}
	/**
	 * @return
	 * 更新实体类
	 */
	public String update(){
		try{
			${fuperLeiName}Service.update(model);
		}catch(Exception e){
			this.getJsonResult().setFlag(false);
			this.getJsonResult().setMsg("系统提示信息："+e.getMessage());
		}
		return JSONRESULT;
	}
	
	/**
	 * @return
	 * 根据id，获取实体类信息
	 */
	public String getById(){
		return getByModel();
	}
	
	/**
	 * @return
	 * 根据实体类设置的属性，获取实体性的信息
	 */
	public String getByModel(){
		try{
			model = ${fuperLeiName}Service.getByModel(model);
		}catch(Exception e){
			this.getJsonResult().setFlag(false);
			this.getJsonResult().setMsg("系统提示信息："+e.getMessage());
		}
		return JSONRESULT;
	}
	
	public ${leiName} getModel() {
		return model;
	}

	public void setModel(${leiName} model) {
		this.model = model;
	}

}
