package za.co.study.casetshidiso.demoing.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;



@Singleton
@DataSourceDefinition(
        name = "java:app/datasource/shopdatasource",
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        url = "jdbc:mysql://mysqldb:3306/shop",
        user = "shopuser",
        password = "shopuserpass"
)
public class DataSourceConfig {
}
