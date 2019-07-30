package mini.mybatis;

import lombok.Data;

@Data
public class Configuration {

    private String jdbcDriver;
    private String jdbcUrl;
    private String username;
    private String password;
}
