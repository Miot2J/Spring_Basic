package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드
    //이렇게 저장하지 말고 파라미터를 다시 리턴하는 식으로 해결해라

    public void order(String name, int price){
        System.out.println("name = " + name +"price = " +price);
        this.price =price; //여기서 문제
        //return price 저장하지말고 이런식으로 변경
    }

    public int getPrice() {
        return price;
    }
}
