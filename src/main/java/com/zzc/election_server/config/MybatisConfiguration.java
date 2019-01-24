package com.zzc.election_server.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.Properties;

/**
 * @author caopengflying
 * @time 2019/1/23 13:11
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfiguration {
    /**
     * 数据库驱动类
     */
    @Value("${mysql.datasource.driverClassName}")
    private String driverClassName = "com.mysql.jdbc.Driver";

    /**
     * 数据库路径
     */
    @Value("${mysql.datasource.url}")
    private String url = "jdbc:mysql://127.0.0.1:3306/zzc_election?autoReconnect=true&useUnicode=true&useSSL=false" +
            "&characterEncoding=UTF-8";

    /**
     * 数据库名称
     */
    @Value("${mysql.datasource.username}")
    private String username = "root";

    /**
     * 数据库密码
     */
    @Value("${mysql.datasource.password}")
    private String password = "root";

    /**
     * 默认提交
     */
    @Value("${mysql.datasource.defaultAutoCommit}")
    private boolean defaultAutoCommit = true;

    /**
     * 连接池的最大数据库连接数。设为0表示无限制。
     */
    @Value("${mysql.datasource.maxActive}")
    private int maxActive = 20;

    /**
     * 最大空闲数，数据库连接的最大空闲时间。超过空闲时间，数据库连接将被标记为不可用，然后被释放。设为0表示无限制。
     */
    @Value("${mysql.datasource.maxIdle}")
    private int maxIdle = 2;

    /**
     * 最小空闲数，
     */
    @Value("${mysql.datasource.minIdle}")
    private int minIdle = 1;

    /**
     * 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
     */
    @Value("${mysql.datasource.maxWait}")
    private int maxWait = 60000;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setMaxActive(maxActive);
        dataSource.setDefaultAutoCommit(defaultAutoCommit);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(120);
        dataSource.setNumTestsPerEvictionRun(30);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource,
                                                   @Qualifier("pageHelper") PageHelper pageHelper) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Interceptor[] plugins = new Interceptor[]{pageHelper};
        bean.setDataSource(dataSource);
        bean.setPlugins(plugins);
        bean.setTypeAliasesPackage("com.zzc.election_server.model");
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setConfigLocation(resolver.getResource("classpath:META-INF/configuration.xml"));
            bean.setMapperLocations(resolver.getResources("classpath:META-INF/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws IOException {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "transactionTemplate")
    public TransactionTemplate getTransactionTemplate(@Qualifier("platformTransactionManager") PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean(name = "pageHelper")
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("reasonable", "false");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("offsetAsPageNum", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的。
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
