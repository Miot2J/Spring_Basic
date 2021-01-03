package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Scope(value = "request")
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    //MyLogger 가 아닌 mylogger를 찾을 수있는 DL 이 들어온다.
//    private final ObjectProvider<MyLogger> myLoggerProvider;


    @RequestMapping("log-demo")
    @ResponseBody //html 화면없이 문자열 바로 반환하고싶을때
    public String logDemo(HttpServletRequest request) {

        String requestUrl = request.getRequestURI().toString();
        System.out.println("myLogger =" +myLogger.getClass());

//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
