package com.again.extend.mybatis.mysql.methods;

import com.baomidou.mybatisplus.core.metadata.TableInfo;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
public class InsertOrUpdateFieldByBatch extends BaseInsertBatch {

	private static final String SQL = "<script>insert into %s %s values %s</script>";

	@Override
	protected String getSql() {
		return "<script>insert into %s %s values %s</script>";
	}

	@Override
	protected String getId() {
		return "insertOrUpdateFieldByBatch";
	}

	@Override
	protected String prepareValuesSqlForMysqlBatch(TableInfo tableInfo) {
		StringBuilder sql = super.prepareValuesBuildSqlForMysqlBatch(tableInfo);
		sql.append(" ON DUPLICATE KEY UPDATE ")
				// 如果模式为 不忽略设置的字段
				.append("<if test=\"!columns.ignore\">")
				.append("<foreach collection=\"columns.list\" item=\"item\" index=\"index\" separator=\",\" >")
				.append("${item.name}=${item.val}").append("</foreach>").append("</if>");

		// 如果模式为 忽略设置的字段
		sql.append("<if test=\"columns.ignore\">")
				.append("<foreach collection=\"columns.back\" item=\"item\" index=\"index\" separator=\",\" >")
				.append("${item}=VALUES(${item})").append("</foreach>").append("</if>");
		return sql.toString();
	}

}
