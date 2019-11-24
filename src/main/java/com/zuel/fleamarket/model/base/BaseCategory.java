package com.zuel.fleamarket.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCategory<M extends BaseCategory<M>> extends Model<M> implements IBean {

	public void setCId(java.lang.Integer cId) {
		set("c_id", cId);
	}
	
	public java.lang.Integer getCId() {
		return getInt("c_id");
	}

	public void setCLabel(java.lang.String cLabel) {
		set("c_label", cLabel);
	}
	
	public java.lang.String getCLabel() {
		return getStr("c_label");
	}

	public void setCAddtime(java.util.Date cAddtime) {
		set("c_addtime", cAddtime);
	}
	
	public java.util.Date getCAddtime() {
		return get("c_addtime");
	}

	public void setCUpdatetime(java.util.Date cUpdatetime) {
		set("c_updatetime", cUpdatetime);
	}
	
	public java.util.Date getCUpdatetime() {
		return get("c_updatetime");
	}

}
