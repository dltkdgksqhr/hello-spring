package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.awt.image.ReplicateScaleFilter;
import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 이 메소드를 만들어준 이유는 테스트 실행시 메소드 실행 순서가 일정하지 않고, 한번 객체에 저장된 데이터를 다음 메소드 실행시 지워주기 위해 사용합니다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
      Member member1 = new Member();
      member1.setName("spring1");
      repository.save(member1);

      Member member2 = new Member();
      member2.setName("spring2");
      repository.save(member2);

      //when
        List<Member> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
