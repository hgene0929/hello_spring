package hello.core.singleton;

public class SingletonService {

    /* 싱글톤 패턴을 적용하여 static 영역에 인스턴스를 1개만 생성 */
    private static final SingletonService instance = new SingletonService();

    /* 사용자 요청에 인스턴스가 필요한 경우 이미 만들어둔 인스턴스를 조회하여 반환 */
    public static SingletonService getInstance() {
        return instance;
    }

    /* 생성자를 private으로 선언하여 외부에서는 객체 생성이 불가하도록 한다 */
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
