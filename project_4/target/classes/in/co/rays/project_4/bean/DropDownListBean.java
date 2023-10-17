package in.co.rays.project_4.bean;


/**
 * DropdownList interface is implemented by Beans those are used to create 
 * drop down list on HTML pages
 * @author ajay
 *
 */
public interface DropDownListBean {

	
	/**
	 * return key of list element
	 * @return key
	 */
	public String getKey();
	
	/**
	 * display list of key element
	 * @return value
	 */
	public String getValue();
}
