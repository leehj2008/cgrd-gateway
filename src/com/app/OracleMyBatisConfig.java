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
@MapperScan(basePackages="com.app.oracle",sqlSessionFactoryRef="oracleSessionFactory")
public class OracleMyBatisConfig {

	@Autowired
	@Qualifier("oracle")
	private DataSource oracleDs;
	
	@Bean
	public SqlSessionFactory oracleSessionFactory() throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(oracleDs);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*Dao.xml"));
		return bean.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplateMysql() throws Exception{
		SqlSessionTemplate tmp = new SqlSessionTemplate(oracleSessionFactory());
		return tmp;
	}
	
	@Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracle") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oracleTransactionTemplate")
    public TransactionTemplate oracleTransactionTemplate(@Qualifier("oracleTransactionManager") PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

}
