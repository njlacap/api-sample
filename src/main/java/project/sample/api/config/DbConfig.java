package project.sample.api.config;

import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig extends H2Dialect {

    @Override
    public boolean dropConstraints() {
        return true;
    }

    @Override
    public boolean supportsIfExistsAfterAlterTable() {
        return true;
    }
}
