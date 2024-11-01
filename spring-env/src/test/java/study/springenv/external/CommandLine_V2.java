package study.springenv.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;

@Slf4j
public class CommandLine_V2 {

//--url=dev_db1 --url=dev_db2 --username=dev_user --password=dev_pw mode=on

    public static void main(String[] args) {
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("SourceArgs: {}", List.of(appArgs.getSourceArgs()));
        log.info("OptionArgs: {}", List.of(appArgs.getOptionNames()));
        log.info("NonOptionArgs: {}", List.of(appArgs.getNonOptionArgs()));

        for (String name : appArgs.getOptionNames()) {
            log.info("option args {}={}", name, appArgs.getOptionValues(name));
        }

        List<String> url = appArgs.getOptionValues("url");
        List<String> username = appArgs.getOptionValues("username");
        List<String> password = appArgs.getOptionValues("password");
        List<String> mode = appArgs.getOptionValues("mode");

        log.info("url={}", url);
        log.info("username={}",username);
        log.info("password={}",password);
        log.info("mode={}",mode);
    }
}
