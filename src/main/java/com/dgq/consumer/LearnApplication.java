package com.dgq.consumer;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.dgq.consumer.dao")
//@ComponentScan(basePackages={"com.dgq.consumer.*"})
public class LearnApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}
	
	/**
	 * 支持tomcat运转
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LearnApplication.class);
    }
	
	private final static String DATASOURCE_PREIFX = "spring.datasource";

	/**
	 * mybatis 1.2.0以上版本取消了dao的自动注入需要手动注入SQLSessionFectory
	 * @return
	 */
//	  @Bean
//	  @ConfigurationProperties(prefix = DATASOURCE_PREIFX)
//	  public DataSource dataSource() {
//	    return new org.apache.tomcat.jdbc.pool.DataSource();
//	  }
//	
//	  @Bean
//	  public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//	
//	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//	    sqlSessionFactoryBean.setDataSource(dataSource());
//	
//	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//	
//	    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//	    return sqlSessionFactoryBean.getObject();
//	  }
//	
//	  @Bean
//	  public PlatformTransactionManager transactionManager() {
//	    return new DataSourceTransactionManager(dataSource());
//	  }
}
