package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

     MemberService memberService = new MemberService();

//    테스트 할 때 메소드 이름을 한글로 적어도 된다.
    @Test
    void 회원가입() {
        //given 주어졌을때
        Member member = new Member();
        member.setName("hello");

        //when 실행하면
        Long saveId = MemberService.join(member);

        //then 결과물이 나온다.
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test //정상 로그도 중요하지만 예외 로그가 가장 중요하다.
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        MemberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }

        //then
    }

    @Test
    void validateDuplicateMember() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}