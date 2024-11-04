package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourceProperties_V1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(MyDataSourceProperties_V1.class)
public class MyDataSourceConfig_V1 {

    private final MyDataSourceProperties_V1 properties;

    @Bean
    public MyDataSource myDataSource() {
        return new MyDataSource(
            properties.getUrl(),
            properties.getUsername(),
            properties.getPassword(),
            properties.getEtc().getMaxConnection(),
            properties.getEtc().getTimeout(),
            properties.getEtc().getOptions()
        );
    }
}
