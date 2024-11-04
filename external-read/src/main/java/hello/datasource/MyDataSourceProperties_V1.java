package hello.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


// MEMO : 타입이 다르면 오류가 발생한다. -> 실수로 다른 값을 넣고 실행하는 것을 방지
@Data
@ConfigurationProperties(prefix = "my.datasource")
public class MyDataSourceProperties_V1 {

    private String url;
    private String username;
    private String password;
    private Etc etc = new Etc();

    @Data
    public static class Etc {
        private int maxConnection;
        private Duration timeout;
        private List<String> options = new ArrayList<>();
    }
}
