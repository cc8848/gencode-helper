<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packpath!}dao.${className}Dao">
	<resultMap id="${className?uncap_first}Map" type="${packpath!}model.${className!}">
		<#list liemodel as col>
		<result property="${col.javaName}" column="${col.coltitle}" /><!-- ${col.comments} -->
		</#list>
	</resultMap>
	<!-- 基本引用sql -->
	<sql id="sql_all_cls">
		select <#list liemodel as col>${col.coltitle}<#if col_has_next>,</#if></#list>
	      from ${tableName} t
	</sql>
	<sql id="sql_where_cls">
		<#list liemodel as col>
		<if test="model.${col.javaName}!=null <#if col.javatype?lower_case?contains("string")>  and model.${col.javaName} != '' </#if>">and t.${col.coltitle} = ${"#{"}model.${col.javaName}}</if>
		</#list>
	</sql>
	<sql id="sql_like_cls">
		<#list liemodel as col>
		<#list likeCols as llike>
	  	<#if col.coltitle?upper_case == llike?upper_case && col.javatype?lower_case?contains("string")>
	  	<#if dbtype=="oracle">
	  	<if test="model.${col.javaName}Like!=null and model.${col.javaName}Like != ''">and UPPER(t.${col.coltitle}) like '%' || UPPER(${"#{"}model.${col.javaName}Like}) || '%'</if>
	  	</#if><#if dbtype=="mysql">
	  	<if test="model.${col.javaName}Like!=null and model.${col.javaName}Like != ''">and UPPER(t.${col.coltitle}) like CONCAT('%',UPPER(${"#{"}model.${col.javaName}Like}),'%')</if>
	  	</#if>
	  	</#if>
		</#list>
	</#list>
	</sql>
	<#if methodStr?index_of("save")!=-1>
	<!-- 保存信息 -->
	<insert id="save"><#if dbtype=="oracle"><selectKey keyProperty="model.${id?split("@")[0]}" order="BEFORE" resultType="long">
			SELECT SEQ_PLATFORM_ID.NEXTVAL FROM DUAL
		</selectKey></#if>
		<![CDATA[
		insert into ${tableName}(
			<#list liemodel as col> ${col.coltitle}<#if col_has_next>,</#if></#list>)
		values (
			<#list liemodel as col>${"#{"}model.${col.javaName}}<#if col_has_next>,</#if></#list>		
		   )
		]]>
	</insert>
	</#if><#if methodStr?index_of("deleteById_")!=-1>
	<!-- 根据主键删除 -->
	<delete id="deleteById">
		<![CDATA[
		delete from ${tableName} where <#assign a=id?split("@")/>${a[0]} = ${"#{"}${a[0]}}
		]]>
	</delete>
	</#if><#if methodStr?index_of("update_")!=-1>
	<!-- 更新信息 -->
	<update id="update" >
		update ${tableName} t
		<set>
	<#list liemodel as col>
			<if test="model.${col.javaName}!=null ">t.${col.coltitle} = ${"#{"}model.${col.javaName}},</if>
	</#list>
		</set>
		where <#assign a=id?split("@")/>${a[0]} = ${"#{"}model.${a[0]}}
	</update>
	</#if><#if methodStr?index_of("getById")!=-1>
	<!-- 根据ID查询-->
	<select id="getById" resultMap="${className?uncap_first}Map">
		<include refid="sql_all_cls" />
		<![CDATA[
		where <#assign a=id?split("@")/>t.${a[0]} = ${"#{"}${a[0]}}
		]]>
	</select>
	</#if><#if methodStr?index_of("getByModel_")!=-1>
	<!-- 带条件的查询 -->
	<select id="getByModel" resultMap="${className?uncap_first}Map">
		<include refid="sql_all_cls" />
		where 1=1 
		<include refid="sql_where_cls" />
	</select>
	</#if><#if methodStr?index_of("searchList_")!=-1>
	<!-- 带条件的查询 -->
	<select id="searchList" resultMap="${className?uncap_first}Map">
		<include refid="sql_all_cls" />
		where 1=1 
		<include refid="sql_where_cls" />
	<!-- 模糊查询 -->
		<include refid="sql_like_cls" />
		order by <#assign a=id?split("@")/>t.${a[0]}
	</select>
	</#if>
</mapper>