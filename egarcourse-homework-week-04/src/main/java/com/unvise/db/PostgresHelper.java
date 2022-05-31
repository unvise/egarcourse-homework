package com.unvise.db;

import com.unvise.config.DatasourceConfig;
import com.unvise.helper.YamlHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresHelper {

    public static Connection getConnection() {
        try {
            DatasourceConfig datasource = YamlHelper.getApplicationConfigFromYaml(YamlHelper.getConfigAsFile())
                    .getDatasource();

            return DriverManager.getConnection(datasource.getUrl(), datasource.getUser(), datasource.getPassword());
        } catch (IOException | URISyntaxException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
