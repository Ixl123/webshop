package hska.iwi.eShopMaster.microservices.categories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hska.iwi.eShopMaster.microservices.users.UsersServer;

@SpringBootApplication
//@EntityScan("hska.iwi.eShopMaster.model.database.dataobjects")
@EnableJpaRepositories("hska.iwi.eShopMaster.microservices.categories.domain")
@PropertySource("classpath:db-config.properties")
public class DBCategoriesConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(DBCategoriesConfiguration.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();		
		String filename = "db-config.properties";
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = DBCategoriesConfiguration.class.getClassLoader().getResourceAsStream(filename);
            properties.load(input);

            dataSource.setDriverClassName(properties.getProperty("spring.jpa.driver_class"));
            dataSource.setUrl(properties.getProperty("spring.jpa.url"));
            dataSource.setUsername(properties.getProperty("spring.jpa.username"));
            dataSource.setPassword(properties.getProperty("spring.jpa.password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataSource;
	}
}
