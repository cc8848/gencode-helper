package gencode.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;
import gencode.common.domain.BeanModel;

/**
 * @author Administrator 生成代码最终
 */
public class GenCodeUtil {
	/**
	 * @param bm
	 * @param tem
	 *            根据实体类，跟模板生成代码
	 * @throws Exception
	 */
	public static void genCode(BeanModel bm) throws Exception {
		Template t = null;
		File file1 = null;
		FileOutputStream fos = null;
		Writer out = null;
		// bm.setMuKuai("order");
		// 实体类
		if (StringUtils.contains(bm.getTypeflag(), "domain_")) {
			t = TemplateUtil.getTem("Domain.ftl");
			file1 = createFile(bm, "domain");
			fos = new FileOutputStream(file1);
			out = new OutputStreamWriter(fos);
			t.process(bm, out);
			out.flush();
		}
		// // dao
		if (StringUtils.contains(bm.getTypeflag(), "dao_")) {
			t = TemplateUtil.getTem("Dao.ftl");
			file1 = createFile(bm, "dao");
			fos = new FileOutputStream(file1);
			out = new OutputStreamWriter(fos);
			t.process(bm, out);
			out.flush();
		}
		// // mapper
		if (StringUtils.contains(bm.getTypeflag(), "mapper_")) {
			t = TemplateUtil.getTem("Mapper.ftl");
			file1 = createFile(bm, "mapper");
			fos = new FileOutputStream(file1);
			out = new OutputStreamWriter(fos);
			t.process(bm, out);
			out.flush();
		}
		// // // service
		// if (StringUtils.contains(bm.getTypeflag(), "service_")) {
		// t = TemplateUtil.getTem("service.ftl");
		// file1 = createFile(bm, "service");
		// fos = new FileOutputStream(file1);
		// out = new OutputStreamWriter(fos);
		// t.process(bm, out);
		// out.flush();
		// }
		// // serviceImpl
		if (StringUtils.contains(bm.getTypeflag(), "serviceimpl_")) {
			t = TemplateUtil.getTem("serviceImpl.ftl");
			file1 = createFile(bm, "serviceImpl");
			fos = new FileOutputStream(file1);
			out = new OutputStreamWriter(fos);
			t.process(bm, out);
			out.flush();
		}
		// // action
		// if (StringUtils.contains(bm.getTypeflag(), "action_")) {
		// t = TemplateUtil.getTem("action.ftl");
		// file1 = createFile(bm, "action");
		// fos = new FileOutputStream(file1);
		// out = new OutputStreamWriter(fos);
		// t.process(bm, out);
		// out.flush();
		// }
		// // jsp
		// if (StringUtils.contains(bm.getTypeflag(), "jsp_")) {
		// t = TemplateUtil.getTem("jsp.ftl");
		// file1 = createFile(bm, "jsp");
		// fos = new FileOutputStream(file1);
		// out = new OutputStreamWriter(fos);
		// t.process(bm, out);
		// out.flush();
		// }
		// // jsp_addupdate
		// if (StringUtils.contains(bm.getTypeflag(), "jspaddupate_")) {
		// t = TemplateUtil.getTem("jsp_addupdate.ftl");
		// file1 = createFile(bm, "jsp_addupdate");
		// fos = new FileOutputStream(file1);
		// out = new OutputStreamWriter(fos);
		// t.process(bm, out);
		// out.flush();
		// }
		//

	}

	private static File createFile(BeanModel bm, String genLayerType) {
		File directory = new File("");// 参数为空
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
			String filePath = "";
			String basepath = "";

			basepath = courseFile
					+ "\\src\\main\\java\\com\\t\\taekwondosysws\\";

			if (StringUtils.contains(genLayerType, "domain")) {
				filePath = basepath + "\\model\\" + bm.getClassName() + ".java";
				bm.setPackpath(StringUtils.replace(basepath + "\\model\\",
						"\\", "."));
			}
			if (StringUtils.contains(genLayerType, "dao")) {
				filePath = basepath + "\\dao\\" + bm.getClassName()
						+ "Dao.java";
				bm.setPackpath(StringUtils.replace(basepath + "\\model\\",
						"\\", "."));
			}
			if (StringUtils.contains(genLayerType, "mapper")) {
				filePath = basepath + "\\mapper\\" + bm.getClassName()
						+ "Mapper.xml";
				bm.setPackpath(StringUtils.replace(basepath + "\\", "\\", "."));
			}
			// if (StringUtils.contains(genLayerType, "service")) {
			// filePath = basepath + "\\service\\I" + bm.getClassName()
			// + "Service.java";
			// bm.setPackpath(StringUtils.replace(basepath + "\\service\\",
			// "\\", "."));
			// }
			if (StringUtils.contains(genLayerType, "serviceImpl")) {
				filePath = basepath + "\\service\\" + bm.getClassName()
						+ "Service.java";
				bm.setPackpath(StringUtils.replace(basepath + "\\service\\",
						"\\", "."));
			}

			//
			// if(StringUtils.isNotBlank(bm.getClspath())){
			// basepath = courseFile + "\\src\\main\\java\\"+bm.getClspath();
			// }else{
			// basepath = courseFile + "\\src\\main\\java\\gencode\\code\\";
			// }
			//
			// if (StringUtils.contains(genLayerType, "domain")) {
			// filePath = basepath +"\\domain\\"+ bm.getClassName() +".java";
			// }
			// if (StringUtils.contains(genLayerType, "dao")) {
			// filePath = basepath +"\\dao\\"+ bm.getClassName() +"Dao.java";
			// }
			// if (StringUtils.contains(genLayerType, "mapper")) {
			// filePath = basepath +"\\mapper\\"+ bm.getClassName()
			// +"Mapper.xml";
			// }
			// if (StringUtils.contains(genLayerType, "service")) {
			// filePath = basepath +"\\service\\"+ bm.getClassName()
			// +"Service.java";
			// }
			// if (StringUtils.contains(genLayerType, "serviceImpl")) {
			// filePath = basepath +"\\service\\impl\\"+ bm.getClassName()
			// +"ServiceImpl.java";
			// }

			bm.setPackpath(StringUtils.replace(bm.getClspath(), "\\", "."));
			File file2 = new File(filePath);
			FileUtils.touch(file2);
			return file2;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
