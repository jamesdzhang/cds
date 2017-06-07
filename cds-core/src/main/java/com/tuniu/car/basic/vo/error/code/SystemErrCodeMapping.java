package com.tuniu.car.basic.vo.error.code;

import java.util.Date;

import com.tuniu.car.utils.JackJsonDateTimeFormat;
import com.tuniu.car.utils.JackJsonDateTimeParse;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class SystemErrCodeMapping {
    private Integer id;
    private String sysCode;
    private Integer errorCode;
    private Integer productClassId;
    private Integer productChildClassId;
    private String content;
    private Integer addUid;

    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date addTime;
    
    private Integer updateUid;
    
    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date updateTime;
    
    private Integer delFlag;
    
    /**
     * 兜底话术标记 0是1否
     */
    private Integer isDefault;
    
    /**
     * 兜底话术资源类型
     */
    private Integer defaultResType;
    
    
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	public Integer getDefaultResType() {
		return defaultResType;
	}
	public void setDefaultResType(Integer defaultResType) {
		this.defaultResType = defaultResType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public Integer getProductClassId() {
		return productClassId;
	}
	public void setProductClassId(Integer productClassId) {
		this.productClassId = productClassId;
	}
	public Integer getProductChildClassId() {
		return productChildClassId;
	}
	public void setProductChildClassId(Integer productChildClassId) {
		this.productChildClassId = productChildClassId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAddUid() {
		return addUid;
	}
	public void setAddUid(Integer addUid) {
		this.addUid = addUid;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
 
}