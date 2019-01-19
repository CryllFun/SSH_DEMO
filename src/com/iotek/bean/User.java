package com.iotek.bean;

import java.util.Date;

public class User {
	private Integer userId;
	private String userName;
	private String userPwd;
	private String email;
	private String mobile;
	private Integer inviteId;
	private String inviteName;
	private String idNo;
	private Date createTime;
	private Date updateTime;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getInviteName() {
		return inviteName;
	}
	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", email=" + email
				+ ", mobile=" + mobile + ", inviteId=" + inviteId + ", inviteName=" + inviteName + ", idNo=" + idNo
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getInviteId() {
		return inviteId;
	}
	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
	}
}
