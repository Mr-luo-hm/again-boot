package com.again.extend.mybatis.mysql;

import com.again.extend.mybatis.config.MybatisConfigurer;
import com.again.extend.mybatis.mysql.methods.InsertByBatch;
import com.again.extend.mybatis.mysql.methods.InsertIgnoreByBatch;
import com.again.extend.mybatis.mysql.methods.InsertOrUpdateByBatch;
import com.again.extend.mybatis.mysql.methods.InsertOrUpdateFieldByBatch;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description:
 */
@Configuration
public class MybatisConfig implements MybatisConfigurer {

	@Override
	public void addIgnoreFields(Set<String> set) {
		set.add("createTime");
	}

	@Override
	public void addGlobalMethods(List<AbstractMethod> list) {
		list.add(new InsertByBatch());
		list.add(new InsertIgnoreByBatch());
		list.add(new InsertOrUpdateByBatch());
		list.add(new InsertOrUpdateFieldByBatch());
	}

}
