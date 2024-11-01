package study.springenv.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLine_V1 {

//    url=devdb username=dev_user password=dev_pw
    public static void main(String[] args) {
        for (String arg : args) {
            log.info(arg);
        }
    }
}
