package hello.core.singleton;

public class SingletonService  {
    //메모리에 해당객체 하나만 생성
    private static final SingletonService instance = new SingletonService();

    //getInstance 매서드를 사용해서 객체값을 얻는다
    public static SingletonService getInstance() {
        return instance;
    }

    //new를 사용한 객체생성 막음
    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
