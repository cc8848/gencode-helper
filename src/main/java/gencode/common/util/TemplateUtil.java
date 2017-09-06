package gencode.common.util;

import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Administrator
 * 根据java类型，获取列类型
 */
public class TemplateUtil {
	/**
	 * @param ftlname
	 * @return
	 * 得到模板
	 */
	public static Template getTem(String ftlname) {
		Configuration cfg = new Configuration();
		// 设定去哪里读取相应的ftl模板文件
        cfg.setClassForTemplateLoading(TemplateUtil.class, "../../template");
		Template temp = null;
		try {
			temp = cfg.getTemplate(ftlname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
}
