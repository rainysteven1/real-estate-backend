package com.rainy.test;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.Test;

import java.util.Collections;

public class CodeGenerator {
    @Test
    public void run() {
        String moduleName = "service_house";
        String modulePathName = "service-house";
        String tableName = "tb_house_files";
        // 数据库配置
        String dbURL = "jdbc:mysql://localhost:13306/real_estate?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        String dbUsername = "root";
        String dbPassword = "123456";

        // 当前项目绝对路径
        String projectPath = System.getProperty("user.dir");
        int index = projectPath.indexOf("/common");
        projectPath = String.format("%s/service/%s", projectPath.substring(0, index), modulePathName);
        // 代码输出目录
        String outputDir = projectPath + "/src/main/java";
        String outputXmlPath = projectPath + "/src/main/resources/mappers";


        FastAutoGenerator.create(dbURL, dbUsername, dbPassword)
                // 全局配置
                .globalConfig(builder ->
                {
                    builder.author("rainy")
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(outputDir);
                })
                // 包配置
                .packageConfig(builder ->
                {
                    builder.parent("com.rainy")
                            .moduleName(moduleName)
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .xml("mapperXml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputXmlPath));
                })
                // 策略配置
                .strategyConfig(builder ->
                {
                    builder.addInclude(tableName)
                            .addTablePrefix("tb_")
                            .enableCapitalMode()
                            // Entity策略配置
                            .entityBuilder()
                            .enableChainModel()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .logicDeleteColumnName("deleted")
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addTableFills(new Column("created", FieldFill.INSERT))
                            .addTableFills(new Column("modified", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)
                            // Mapper策略配置
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            //Service策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            // Controller策略配置
                            .controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


}

