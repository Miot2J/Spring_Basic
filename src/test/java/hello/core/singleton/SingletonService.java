package hello.core.singleton;

public class SingletonService {
    //static 영역에 싱글톤 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();
    //객체 인스턴스 필요시 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }
    //생성자를 private로 선언해 외부에서 new 키워드 사용한 객체생성 막는다.
    private SingletonService(){

    }

    public void  logic(){
        System.out.println("싱글톤 로직 호출");
   }
    public static void main(String[] args) {
        SingletonService singletonService = new SingletonService();
    }
}
