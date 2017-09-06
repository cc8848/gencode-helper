<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/comm/inc.jsp" %>
</head>
<body>
	<div style="background:#fff;">
		<form id="submit_form">
		<s:hidden name="model.id"/>
			<table class="form_table"  style="width:80%;">
			<#list liemodel as col>
				<#if col_index%2==0>
				<tr>
				</#if>
					<td class="col_right">${col.zhushi}</td>
					<td><s:textfield name="model.${col.lieJaveName}" cssClass="validate[required]"/></td>
				<#if col_index%2==1 || !col_has_next>
				</tr>
				</#if>
			</#list>
				<tr>
					<td colspan="4">
					<a id="btn_add" class="easyui-linkbutton" 
						data-options="iconCls:'icon01_tick'">提交</a> 
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$("#btn_add").on("click",function(){
			var url="${"${"}webroot}/basic/${fuperLeiName}_saveOrupdate";
			if(!$('#submit_form').validationEngine('validate')){
				return;
			}
			$.post(url,$("#submit_form").serialize(),function(rd){
				if(rd.flag){
					layer.alert('提交成功！', function(index){
					    layer.close(index);
						location.href="${"${"}webroot}/basic/${fuperLeiName}_main";
					}); 
				}else{
					layer.alert(rd.msg);
				}
			});
		});
	</script>
</body>
</html>
