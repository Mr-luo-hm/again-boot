package com.again.extend.mybatis.mysql.methods;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
public class InsertIgnoreByBatch extends BaseInsertBatch {

	@Override
	protected String getSql() {
		return "<script>insert ignore into %s %s values %s</script>";
	}

	@Override
	protected String getId() {
		return "insertIgnoreByBatch";
	}

}