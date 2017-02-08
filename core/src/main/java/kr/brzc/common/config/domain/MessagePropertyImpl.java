package kr.brzc.common.config.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.copy.CreateResponse;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransform;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformMember;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformTypes;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.locale.domain.LocaleImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationDataDrivenEnumeration;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;


/**
 * brz-common package
 * 
 * DB에서 동적으로 Message Properties를 조회하는 서비스 엔티티
 * 
 * @author Kunner, kunner@kunner.com
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@javax.persistence.Table(name = "BRZ_MESSAGE_PROPERTY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "blStandardElements")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "MessagePropertyImpl_baseClass")
@DirectCopyTransform({
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.SANDBOX, skipOverlaps = true)
})
public class MessagePropertyImpl implements MessageProperty, AdminMainEntity, ProfileEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "MessagePropertyId")
    @GenericGenerator(
            name = "MessagePropertyId",
            strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name = "segment_value", value = "MessagePropertyImpl"),
                    @Parameter(name = "entity_name", value = "kr.brzc.common.config.domain.MessagePropertyImpl")
            })
    @Column(name = "MESSAGE_PROPERTY_ID")
    protected Long id;
	
	@Column(name = "MESSAGE_ENTITY")
	@AdminPresentation(friendlyName="MessagePropertyImpl_Entity", order=50, prominent = true, gridOrder=5, requiredOverride=RequiredOverride.REQUIRED)
	protected String messageEntity;

	@Column(name = "MESSAGE_KEY")
	@AdminPresentation(friendlyName="MessagePropertyImpl_Key", order=100, prominent = true, gridOrder=10, requiredOverride=RequiredOverride.REQUIRED)
	protected String key;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "MESSAGE_VALUE", nullable = true, length = Integer.MAX_VALUE - 1)
	@AdminPresentation(friendlyName="MessagePropertyImpl_Value"
	, order=200
	, prominent = true
	, gridOrder=100
	, requiredOverride=RequiredOverride.REQUIRED
	, largeEntry = true // LOB 데이터인 경우 
	)
	protected String value;
	
	
	@Column(name = "MESSAGE_DESC")
	@AdminPresentation(friendlyName="MessagePropertyImpl_Description", order=300)
	protected String description;
	
	
    @Column(name = "MESSAGE_LOCALE_CODE")
    @AdminPresentation(friendlyName="MessagePropertyImpl_Locale", order=250, prominent = true, gridOrder=50, defaultValue="en_US", requiredOverride=RequiredOverride.REQUIRED)
    @AdminPresentationDataDrivenEnumeration(optionListEntity = LocaleImpl.class, optionValueFieldName= "localeCode", optionDisplayFieldName="friendlyName", optionCanEditValues = false)
    protected String locale;
	
	
	
	@Override
	public Long getId(){
		return id;
	}
	
	@Override
	public void setId(Long id){
		this.id = id;
	}
	
	@Override
	public String getMessageEntity(){
		return messageEntity;
	}
	
	@Override
	public void setMessageEntity(String messageEntity){
		this.messageEntity = messageEntity;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getLocale() {
		return locale;
	}

	@Override
	public void setLocale(String locale) {
		this.locale = locale;
	}

	
	@Override
	public <G extends MessageProperty> CreateResponse<G> createOrRetrieveCopyInstance(MultiTenantCopyContext context)
			throws CloneNotSupportedException {
		CreateResponse<G> createResponse = context.createOrRetrieveCopyInstance(this);
        if (createResponse.isAlreadyPopulated()) {
            return createResponse;
        }
        MessageProperty cloned = createResponse.getClone();
        cloned.setMessageEntity(messageEntity);
        cloned.setKey(key);
        cloned.setValue(value);
        cloned.setDescription(description);
        cloned.setLocale(locale);

        return createResponse;
		
	}

	@Override
	public String getMainEntityName() {
		return getKey();
	}
	
	
}
