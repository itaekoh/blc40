package kr.brzc.common.config.domain;

import java.io.Serializable;

import org.broadleafcommerce.common.copy.MultiTenantCloneable;

/**
 * 
 * brz-common package
 * 
 * @author Kunner, kunner@kunner.com
 *
 */
public interface MessageProperty extends MultiTenantCloneable<MessageProperty>, Serializable{
	
	Long getId();
	void setId(Long id);
	
	String getKey();
	void setKey(String key);
	
	String getValue();
	void setValue(String value);
	
	String getDescription();
	void setDescription(String description);
	
	String getLocale();
	void setLocale(String locale);
	
	String getMessageEntity();
	void setMessageEntity(String messageEntity);

}
