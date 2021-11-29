package com.again.boot.starter.pv.annotation;

import java.lang.annotation.*;

import com.again.boot.starter.pv.config.PvConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ PvConfiguration.class })
public @interface EnablePvLog {

}
