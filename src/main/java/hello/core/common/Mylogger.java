package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //request 빈 스코프를 가진 스프링 빈 생성 프록시도 사용해준다
public class Mylogger {

    private String uuid; //사용자 요청을 구분하기 위한 uid
    private String requestURL; //사용자 요청 주소

    //사용자의 요청을 받을 때 url이 정해진다다
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    //로그를 남기기 위한 메서드
    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    /* request 빈스코프는 http 요청을 실행 기준으로 잡기 때문에
    *  서로 다른 요청이 들어올 때마다 초기화 메서드가 실행된다
    *  따라서 초기화 메서드에 uuid 생성 로직을 구현하면 모든 http 요청이 서로 다른 uuid를 가진다 */
    @PostConstruct
    public void init() {
        //사용자 요청을 구분하기 위한 유니크한 아이디 생성( 사용자 요청이 들어오면 자동생성 )
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] request scope bean create : "+this);
    }
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] request scope bean close : "+this);
    }
}
