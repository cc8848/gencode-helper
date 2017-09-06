<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	
<!DOCTYPE html>
<html>
<head>
<%@ include file="/comm/inc.jsp" %>
<script type="text/javascript">
	$(function(){
		var tt = $("#tt").datagrid({   
			title:"xx列表",
		    url:'${"${"}webroot}/basic/${fuperLeiName}_searchPager',
		    toolbar:"#tools_bar",
		    pagination:true,
		    fit:true,
		    fitColumns:true,
		    columns:[[    
		        <#list liemodel as col>
		        <#if col.lieJaveName == "id">
		        {field:'id',checkbox:true},
		        <#else>
				{field:'${col.lieJaveName}',title:'${col.zhushi}',width:100}<#if col_has_next>,</#if> 
				</#if>   
				</#list>
		    ]]
		});
		$("#btn_reset").on("click",function(){
			$("#submit_form").find(":input").val("");
		});
		$("#btn_search").on("click",function(){
			tt.datagrid("reload",$("#submit_form").serializeJson());
		});
		$("#btn_export").on("click",function(){
			tt.datagrid("reload",$("#submit_form").serializeJson());
		});
		
		$("#btn_update").on("click",function(){
			var gd = tt.datagrid("getChecked");
			if(gd.length==1){
				location.href="${"${"}webroot}/basic/${fuperLeiName}_toaddupdate?model.id="+gd[0].id;
			}else{
				layer.alert('请选择一条数据操作！'); 
			}
		});
		$("#btn_del").on("click",function(){
			var gd = tt.datagrid("getChecked");
			if(gd.length==1){
				layer.confirm('确认删除?',{icon: 3, title:'提示'}, function(index){
					$.post("${"${"}webroot}/basic/${fuperLeiName}_delete",{"model.id":gd[0].id},function(rd){
						if(rd.flag){
							tt.datagrid("reload");
							layer.msg('删除成功！');
						}else{
							layer.alert(rd.msg); 
						}				
					});
				    layer.close(index);
				}); 
			}else{
				layer.alert('请选择一条数据操作！'); 
			}
		});
	});
</script>
</head>
<body>
	<table id="tt"></table>
	<div id="tools_bar" style="background:#fff;">
		<form id="submit_form">
		<table class="form_table"  style="width:100%;">
		<#list liemodel as col>
			<#if col_index%3==0>
			<tr>
			</#if>
				<td class="col_right">${col.zhushi}</td>
				<td><input name="model.${col.lieJaveName}" type="text"></td>
			<#if col_index%3==2 || !col_has_next>
			</tr>
			</#if>
		</#list>
			<tr>
				<td colspan="6">
					<a id="btn_add" href="${"${"}webroot}/basic/${fuperLeiName}_toaddupdate" class="easyui-linkbutton" data-options="iconCls:'icon01_action_add'">新建</a> 
					<a id="btn_update" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon01_reply'">修改</a> 
					<a id="btn_del" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon01_action_delete'">删除</a> 
					<a id="btn_reset" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon02_arrow_circle_double'">重置</a> 
					<a id="btn_search" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
					<a id="btn_export" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon02_arrow_270'">导出EXCEL</a> 
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
