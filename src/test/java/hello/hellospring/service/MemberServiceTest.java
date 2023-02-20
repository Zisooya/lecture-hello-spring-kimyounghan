package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 각 테스트가 실행되기 전 실행할 동작
    @BeforeEach
    public void beforeEach(){
        // 의존성 주입(dependency injection)
        // memberRepository를 MemberService 클래스에 있는 memberRepository객체로 넣어줌.(직접 new 하지 않음.)
        // => 같은 MemoryMemberRepository객체를 사용하게 됨.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 아래 메소드들의 동작이 끝날 때마다 실행될 동작.
    // 테스트는 의존관계 없이 설계되어야 하므로 각 메소드 테스트 시 마지막에 저장소나 공용데이터들을 깔끔하게 지워줘야 한다.
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // memberService.join(member2)로직이 실행되면, IllegalStateException 예외가 터져야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}