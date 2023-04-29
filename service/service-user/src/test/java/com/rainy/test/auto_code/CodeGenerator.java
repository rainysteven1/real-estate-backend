package com.rainy.test.auto_code;

import org.junit.Test;

public class CodeGenerator {
    @Test
    public void run() {
        // 数据库配置
        String dbURL = "jdbc:mysql://localhost:13306/tb_userinfo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        String dbUsername = "root";
        String dbPassword = "123456";

        // 当前项目绝对路径
        String projectPath = System.getProperty("user.dir");
        // 代码输出目录
        String outputDir = projectPath + "/src/main/java";
        System.out.println(outputDir);

    }
}
