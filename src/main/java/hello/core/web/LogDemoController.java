package hello.core.web;

import hello.core.common.Mylogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final Mylogger mylogger;

    @RequestMapping("log-demo")
    @ResponseBody //따로 출력내용 없이 반환값을 메서드 자체로 설정
    public String logDemo(HttpServletRequest request) {
        //고객의 요청 주소 지정
        String requestURL = request.getRequestURL().toString();
        mylogger.setRequestURL(requestURL);

        mylogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
