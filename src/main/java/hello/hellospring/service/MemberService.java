package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { //test 파일 만들기 : ctrl + shift +t
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member); // 메소드 분리 ctrl + alt + M

        /* result 의 반환값이 Optional<Member> 이므로 위에처럼 써도 됨
        *   Optional<Member> result = memberRepository.findByName(member.getName()); //반환값 만들어줌 : ctrl + alt + v
            result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
            });
        *
        * */

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //반환값 만들어줌 : ctrl + alt + v
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMessage(){
        return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

