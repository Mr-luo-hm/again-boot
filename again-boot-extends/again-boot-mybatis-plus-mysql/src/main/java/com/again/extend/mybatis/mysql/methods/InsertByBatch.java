package com.again.extend.mybatis.mysql.methods;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
public class InsertByBatch extends BaseInsertBatch {

	@Override
	public boolean backFillKey() {
		return true;
	}

	@Override
	protected String getSql() {
		return "<script>insert into %s %s values %s</script>";
	}

	@Override
	protected String getId() {
		return "insertByBatch";
	}

}
