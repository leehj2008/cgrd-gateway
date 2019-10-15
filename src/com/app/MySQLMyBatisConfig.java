package com.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@MapperScan(basePackages="com.app.dao",sqlSessionFactoryRef="mysqlSessionFactory")
public class MySQLMyBatisConfig {

	@Autowired
	@Qualifier("mysql")
	private DataSource mysqlDs;
	
	@Bean
	@Primary
	public SqlSessionFactory mysqlSessionFactory() throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(mysqlDs);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*Dao.xml"));
		return bean.getObject();
	}
	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplateMysql() throws Exception{
		SqlSessionTemplate tmp = new SqlSessionTemplate(mysqlSessionFactory());
		return tmp;
	}
	
	@Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysql") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlTransactionTemplate")
    @Primary
    public TransactionTemplate mysqlTransactionTemplate(@Qualifier("mysqlTransactionManager") PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }
}
