package com.klarity.common.dal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.klarity.common.dal.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis mapper自动生成工具类
 *
 * @author: pengcheng091
 */
public class MybatisPlusGenerator {


    /**
     * 创建人
     */
    public static final String AUTHOR = "pengcheng091";


    /**
     * 数据库连接地址
     */
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/klarity";


    /**
     * 数据库连接用户名
     */
    public static final String DB_NAME = "root";

    /**
     * 数据库连接密码
     */
    public static final String DB_PWD = "root";


    /**
     * 包路径
     */
    public static final String PKG_PATH = "com.klarity.common.dal";

    /**
     * 项目路径
     */
    public static final String PROJECT_PATH = "/main/common/klarity-common-dal";

    public static void main(String[] args) {
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(DB_URL, DB_NAME, DB_PWD);
        fastAutoGenerator.globalConfig(builder -> {
            String projectPath = System.getProperty("user.dir") + PROJECT_PATH;
            builder.outputDir(projectPath + "/src/main/java")
                    .author(AUTHOR)
                    //.fileOverride()
                    .disableOpenDir();
        });
        fastAutoGenerator.packageConfig(builder -> {
            builder.parent(PKG_PATH);
            builder.entity("entity");
            builder.mapper("mapper");
            Map<OutputFile, String> packageInfo = new HashMap<>();
            packageInfo.put(OutputFile.mapperXml, "." + PROJECT_PATH + "/src/main/resources/mapper/");
            builder.pathInfo(packageInfo);
        });
        fastAutoGenerator.strategyConfig(builder -> {
            builder.addTablePrefix("tbl_klarity");
            builder.serviceBuilder().superServiceClass(IService.class);
            builder.serviceBuilder().superServiceImplClass(ServiceImpl.class);
            builder.serviceBuilder().convertServiceFileName(new ConverterFileName() {
                @Override
                public String convert(String entityName) {
                    return entityName + "DaoService";
                }
            });
            builder.serviceBuilder().convertServiceImplFileName(new ConverterFileName() {
                @Override
                public String convert(String entityName) {
                    return entityName + "DaoServiceImpl";
                }
            });
            //实体类
            builder.entityBuilder().convertFileName(new ConverterFileName() {
                @Override
                public String convert(String entityName) {
                    return entityName + "Entity";
                }
            });
            builder.entityBuilder().superClass(BaseEntity.class);
            builder.entityBuilder().addSuperEntityColumns("id", "create_time", "creator", "modify_time", "modifier", "deleted", "tenant_id");
            builder.entityBuilder().enableLombok();
            builder.entityBuilder().enableChainModel();
            builder.entityBuilder().enableTableFieldAnnotation();
            //xml
            builder.mapperBuilder();
        });

        fastAutoGenerator.templateConfig(builder -> {
            builder.controller("");
            //builder.service("");
            //builder.serviceImpl("");
        });

        fastAutoGenerator.execute();
    }
}
