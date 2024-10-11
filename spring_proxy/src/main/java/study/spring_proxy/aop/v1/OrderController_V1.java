package study.spring_proxy.aop.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/v1")
@ResponseBody
public interface OrderController_V1 {
    @GetMapping("/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/no-log")
    String noLog();
}
