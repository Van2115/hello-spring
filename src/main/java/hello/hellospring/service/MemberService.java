package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { //test 쉽게 하기 해당 클래스에 command + shift + t

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //memberservice에 있는 MemoryMember~와 test에 있는 MemoryMember서로 다른 인스턴스임(리퐈지토리) 테스트는 같은 인스턴스를 사용해야하기 때문에 아래처럼 바꿈

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); -> 이걸 줄여서
//        memberRepository.findByName(member.getName())
//                        .ifPresent(m ->{
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        }); -> 이렇게 보이면 메소드로 뽑는게 가장 좋음 단축키 contral + T -> extra~ method 선택
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
