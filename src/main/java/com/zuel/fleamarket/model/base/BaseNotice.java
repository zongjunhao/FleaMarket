package com.zuel.fleamarket.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseNotice<M extends BaseNotice<M>> extends Model<M> implements IBean {

	public void setNId(java.lang.Integer nId) {
		set("n_id", nId);
	}
	
	public java.lang.Integer getNId() {
		return getInt("n_id");
	}

	public void setNUId(java.lang.Integer nUId) {
		set("n_u_id", nUId);
	}
	
	public java.lang.Integer getNUId() {
		return getInt("n_u_id");
	}

	public void setNName(java.lang.String nName) {
		set("n_name", nName);
	}
	
	public java.lang.String getNName() {
		return getStr("n_name");
	}

	public void setNPrice(java.lang.Integer nPrice) {
		set("n_price", nPrice);
	}
	
	public java.lang.Integer getNPrice() {
		return getInt("n_price");
	}

	public void setNDescribe(java.lang.String nDescribe) {
		set("n_describe", nDescribe);
	}
	
	public java.lang.String getNDescribe() {
		return getStr("n_describe");
	}

	public void setNState(java.lang.Integer nState) {
		set("n_state", nState);
	}
	
	public java.lang.Integer getNState() {
		return getInt("n_state");
	}

	public void setNAddtime(java.util.Date nAddtime) {
		set("n_addtime", nAddtime);
	}
	
	public java.util.Date getNAddtime() {
		return get("n_addtime");
	}

	public void setNUpdatetime(java.util.Date nUpdatetime) {
		set("n_updatetime", nUpdatetime);
	}
	
	public java.util.Date getNUpdatetime() {
		return get("n_updatetime");
	}

}
