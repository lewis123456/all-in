package cn.lewis.all_in.server;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class AllInConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(AllInConfig.class);

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.driver.class.name}")
    private String jdbcDriverClassName;

    @Value("${jdbc.initial.size}")
    private int jdbcInitialSize;

    @Value("${jdbc.min.idle}")
    private int jdbcMinIdle;

    @Value("${jdbc.max.active}")
    private int jdbcMaxActive;

    @Value("${jdbc.max.wait}")
    private int jdbcMaxWait;

    @Value("${jdbc.time.between.eviction.run.millis}")
    private int jdbcTimeBetweenEvictionRunMillis;

    @Value("${jdbc.min.evictable.idle.time.millis}")
    private int jdbcMinEvictableIdleTimeMillis;

    @Value("${jdbc.test.validation.query}")
    private String jdbcValidationQuery;

    @Value("${jdbc.test.while.idle}")
    private boolean jdbcTestWhileIdle;

    @Value("${jdbc.test.on.borrow}")
    private boolean jdbcTestOnBorrow;

    @Value("${jdbc.test.on.return}")
    private boolean jdbcTestOnReturn;

    @Value("${jdbc.pool.prepared.statements}")
    private boolean jdbcPoolPreparedStatements;

    @Value("${jdbc.max.pool.prepared.statement.per.connection.size}")
    private int jdbcMaxPoolPreparedStatementPerConnectionSize;

    @Value("${jdbc.filters}")
    private String jdbcFilters;

    @Value("${jdbc.connection.properties}")
    private String jdbcConnectionProperties;

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.pool.max-active}")
    private int redisPoolMaxActive;

    @Value("${redis.pool.max-wait}")
    private int redisPoolMaxWait;

    @Value("${redis.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${redis.pool.min-idle}")
    private int redisPoolMinIdle;

    @Value("${redis.timeout}")
    private int redisTimeout;

    @Bean(value = "dataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(jdbcUrl);
        datasource.setUsername(jdbcUsername);
        datasource.setPassword(jdbcPassword);
        datasource.setDriverClassName(jdbcDriverClassName);

        datasource.setInitialSize(jdbcInitialSize);
        datasource.setMinIdle(jdbcMinIdle);
        datasource.setMaxActive(jdbcMaxActive);
        datasource.setMaxWait(jdbcMaxWait);
        datasource.setTimeBetweenEvictionRunsMillis(jdbcTimeBetweenEvictionRunMillis);
        datasource.setMinEvictableIdleTimeMillis(jdbcMinEvictableIdleTimeMillis);
        datasource.setValidationQuery(jdbcValidationQuery);
        datasource.setTestWhileIdle(jdbcTestWhileIdle);
        datasource.setTestOnBorrow(jdbcTestOnBorrow);
        datasource.setTestOnReturn(jdbcTestOnReturn);
        datasource.setPoolPreparedStatements(jdbcPoolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(jdbcMaxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(jdbcFilters);
        } catch (SQLException e) {
            LOG.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(jdbcConnectionProperties);
        return datasource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setDataSource(ds);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "jedisPool")
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisPoolMaxIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, redisTimeout);
        return jedisPool;
    }
}
