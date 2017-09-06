package gencode.common;

import gencode.common.domain.BeanModel;


public interface BeanService {
	/**
	 * @param dbtype
	 * @return
	 */
	public BeanModel getBeanModel(String driver, String url, String username, String pwd,String tname,String scheme);
}
