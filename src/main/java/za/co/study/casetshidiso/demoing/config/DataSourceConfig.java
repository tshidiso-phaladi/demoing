package za.co.study.casetshidiso.demoing.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.ejb.Singleton;

@Singleton
@DataSourceDefinitions({
        @DataSourceDefinition(
                name = "java:app/datasource/userdatasource",
                className = "com.mysql.cj.jdbc.MysqlDataSource",
                url = "jdbc:mysql://mysqldb:3306/shop",
                user = "shopuser",
                password = "shopuserpass"
        ),
        @DataSourceDefinition(
                name = "java:app/datasource/productdatasource",
                className = "com.mysql.cj.jdbc.MysqlDataSource",
                url = "jdbc:mysql://mysqldb:3306/shop",
                user = "shopuser",
                password = "shopuserpass"
        )
})
public class DataSourceConfig {
}
