package com.core.auto;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Created by South on 2020/10/29.
 * 自动生成代码配置类
 */
public class MybatisPlusGeneratorUtils {

    //生成文件目录
    private String basePath = System.getProperty("user.dir") + "\\src\\main\\java";
    //生成文件基础包
    private String basePage = this.getClass().getPackage().getName().replace(".auto", "");
    //作者
    private String Author;
    //数据库账号
    private String dbUserName;
    //数据库密码
    private String dbPassWord;
    //数据地址
    private String dbUrl;
    //数据库驱动
    private String dbDriverName;
    //实体类继承类
    private String modelBaseClass = basePage + ".utils.PageInfoModel";


    public MybatisPlusGeneratorUtils init(String Author, MybatisDataSourceConfig springBootApplicationConfig) {
        this.Author = Author;
        this.dbUserName = springBootApplicationConfig.getSpringDatasourceUserName();
        this.dbPassWord = springBootApplicationConfig.getSpringDatasourcePassWord();
        this.dbUrl = springBootApplicationConfig.getSpringDatasourceUrl();
        this.dbDriverName = springBootApplicationConfig.getSpringDatasourceDriverClassName();
        return this;
    }


    public void createTableInfo(String... tableNames) {
        if (tableNames.length == 0) {
            return;
        }
        AutoGenerator auto = new AutoGenerator();

        //todo: 全局配置
        auto.setGlobalConfig(new GlobalConfig()
                        // 覆盖输出到xxx目录
                        .setFileOverride(true).setOutputDir(basePath)
                        // 生成BaseResultMap
                        .setBaseResultMap(true)
                        // 生成Base_Column_List
                        .setBaseColumnList(true)
                        // 指定作者
                        .setAuthor(Author)
                        // swagger2注解
                        .setSwagger2(true)
                        // 日期生成策略
                        .setDateType(DateType.ONLY_DATE)
                        // 是否覆盖原来的文件
                        .setFileOverride(true)
                        // 是否生成后打开资源管理器
                        .setOpen(false)
                        // 设置Controller、Service、ServiceImpl、Dao、Mapper文件名称，%s是依据表名转换来的
                        // 这里的名称要与模板中进行对应
                        .setServiceName("I%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setMapperName("I%sDao")
                        .setXmlName("I%sDao")
//                .setXmlName("Admin%sController")
        );

        //todo: 数据源配置
        auto.setDataSource(new DataSourceConfig()
                // 用户名、密码、驱动、url
                .setUsername(dbUserName).setPassword(dbPassWord)
                .setDbType(DbType.MYSQL).setDriverName(dbDriverName)
                .setUrl(dbUrl));

        //todo: 包名配置
        auto.setPackageInfo(new PackageConfig()
                // 父包名
                .setParent(basePage)
                // 模块名
                //.setModuleName("model")
                // 分层包名
                .setService("service")
                .setServiceImpl("service.impl")
                .setEntity("model")
                .setMapper("dao")
                .setXml("xml"));

        //todo: 策略配置
        auto.setStrategy(new StrategyConfig()
                // 命名策略：实体的类名和属性名按下划线转驼峰 user_info -> userInfo
                .setNaming(NamingStrategy.underline_to_camel)
                // 表列名规则
                //.setColumnNaming(NamingStrategy.underline_to_camel)
                // controller生成@RestCcontroller
                .setRestControllerStyle(true)
                // 设置表前缀
                .setTablePrefix("t_")
                // 设置表名，否则全部表
                .setInclude(tableNames)
                // 是否生成lombok注解
                .setEntityLombokModel(true)
                .setSuperEntityClass(modelBaseClass)
                // controller格式
                .setRestControllerStyle(true));

        //todo: 自定义配置
        auto.setTemplate(new TemplateConfig()
                // 设置controller模板
//                .setController("template/Controller.java.vm")
                // 设置service模板
                .setService("template/Service.java.vm")
                // 设置serviceImpl模板
                .setServiceImpl("template/ServiceImpl.java.vm")
                // 设置实体类模板
                .setEntity("template/Entity.java.vm")
                // 设置dao模板
                .setMapper("template/Dao.java.vm")
                // 设置xml模板
                .setXml("template/Mapper.xml.vm")
        );
        //todo: 执行生成
        auto.execute();
    }


}
