package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //동시성 문제가 있을 수 있으어 독립되는 콘커리트맵? 그걸 써야 하나 단순 예제로 HashMap 사용
    private  static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L;

    @Override
    public Member sava(Member member) {


        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                //.filter(member -> memeber.getName().equals(name))
                .findAny(); //하나 찾아지는 것 끝까지 찾았는데 없으면 Optional로 감싸사
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
