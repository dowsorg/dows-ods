package org.dows.ods;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.dows.ods.mapper.*", sqlSessionTemplateRef = "odsSqlSessionTemplate")
public class OdsDataSourceConfig {


    @Bean(name = "odsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource odsDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "odsSqlSessionFactory")
    public SqlSessionFactory odsSqlSessionFactory(@Qualifier("odsDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        properties.setProperty("reasonable", "false");
        // 添加插件
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "odsTransactionManager")
    public DataSourceTransactionManager odsTransactionManager(@Qualifier("odsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "odsSqlSessionTemplate")
    public SqlSessionTemplate odsSqlSessionTemplate(@Qualifier("odsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}