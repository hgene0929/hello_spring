package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    /* 접속해야 할 서버의 url */
    private String url;

    public NetworkClient() {
        /* 생성자 호출 : 생성자 내부에서는 웬만하면 외부의 연결 등은 구현하지 않아야 한다 */
        System.out.println("생성자 호출 = "+url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /* 네트워크 연결 열어두는 작업 (가상으로 url 출력기능으로 대체) */
    public void connect() {
        System.out.println("connect = "+ url);
    }

    /* 사용자 호출시 네트워크 사용 (가상으로 url/메시지 출력기능으로 대체) */
    public void call(String message) {
        System.out.println("call = "+url+" message = " + message);
    }

    /* 서비스 종료시 네트워크 닫는 작업 (가상으로 대체) */
    public void disconnect() {
        System.out.println("close = " + url);
    }

    /* (3) 어노테이션을 사용한 콜백메서드 방식
    초기화에 사용할 메서드 생성 */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    /* 종료에 사용할 메서드 생성 */
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /* (2) 설정정보를 사용한 콜백메서드 방식
    초기화에 사용할 메서드 생성
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    종료에 사용할 메서드 생성
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    */

    /* (1) 인터페이스 사용한 콜백메서드 방식
    스프링 생성시 초기화 메서드
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    스프링 소멸 직전 종료 메서드
    @Override
    public void destroy() throws Exception {
        disconnect();
    }
     */
}
