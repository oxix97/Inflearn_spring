package study.spring_proxy.aop.v1;

import org.springframework.web.bind.annotation.*;

@RestController
public interface OrderController_V1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
