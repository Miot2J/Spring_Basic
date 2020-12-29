package hello.core.order;

import hello.core.Discount.DiscountPolicy;
//import hello.core.Discount.FixDiscountPolicy;
//import hello.core.Discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 클래스내의 final 붙은 변수를 가지고 생성자 실행해줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


//    setter 방식의 의존관계 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
//         this.memberRepository = memberRepository;
//    }

//    일반 메서드 의존관계 주입
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자 방식의 의존관계 주입 // 현재 qualifier 써서 사용(중복 타입 에러 방지 위함)
    // **현재 롬복을 사용하여 생성자 선언 안해도 알아서 처리해준다
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//   }

    //RateDiscountPolicy에  @Primary 지정하여 최우선적으로 선택되게 사용(main과 sub DB 비율이 9:1 정도 일때 main에 primary sub에 primary)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
