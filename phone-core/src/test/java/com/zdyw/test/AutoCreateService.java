package com.zdyw.test;

import com.core.ZdywCoreApplication;
import com.core.auto.MybatisDataSourceConfig;
import com.core.auto.MybatisPlusGeneratorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Miracle
 * @date 2020/12/3 14:45
 */
@SpringBootTest(classes = ZdywCoreApplication.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AutoCreateService {


    @Test
    public void masterMiracleAotuCreateTable() {
        MybatisDataSourceConfig mybatisDataSourceConfig=new MybatisDataSourceConfig();
        mybatisDataSourceConfig.setSpringDatasourcePassWord("8z3k5bZzyabrxfc7");
        mybatisDataSourceConfig.setSpringDatasourceUserName("phone");
        mybatisDataSourceConfig.setSpringDatasourceDriverClassName("com.mysql.cj.jdbc.Driver");
        mybatisDataSourceConfig.setSpringDatasourceUrl("jdbc:mysql://47.242.11.16:3306/phone?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&rewriteBatchedStatements=true&serverTimezone=GMT&allowPublicKeyRetrieval=true");
        MybatisPlusGeneratorUtils mybatisPlusGeneratorUtils=new MybatisPlusGeneratorUtils();
        mybatisPlusGeneratorUtils.init("Miracle",mybatisDataSourceConfig);
        mybatisPlusGeneratorUtils.createTableInfo("t_part");
    }














}
