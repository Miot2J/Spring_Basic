package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;


//롬복으로 게터세터 메서드 생성할 필요없이 관리해준다.
@Getter
@Setter
@ToString
public class HelloLombok {
    private  String name;
    private  int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("aasese");
        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
