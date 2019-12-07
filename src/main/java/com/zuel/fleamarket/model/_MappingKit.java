package com.zuel.fleamarket.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("admin", "a_id", Admin.class);
		arp.addMapping("category", "c_id", Category.class);
		arp.addMapping("comment", "com_id", Comment.class);
		arp.addMapping("follow", "f_id", Follow.class);
		arp.addMapping("goods", "g_id", Goods.class);
		arp.addMapping("notice", "n_id", Notice.class);
		arp.addMapping("user", "u_id", User.class);
	}
}

