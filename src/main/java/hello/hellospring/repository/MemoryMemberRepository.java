package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //동시성 문제가 있을 수 있으어 독립되는 콘커리트맵? 그걸 써야 하나 단순 예제로 HashMap 사용
    private  static Map<Long, Member> store = new HashMap<>(); //실무에ㅣ선 HashMap 보단 동시성 문제 때문에 ConcurrentHashMap뭐시기를 사용

    private  static long sequence = 0L; //키 값을 0 , 1 만들어주는 거라고 생각 하면됨 -> 실무에선 다른거 사용

    @Override
    public Member save(Member member) {
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
                .filter(member -> member.getId().equals(name)) //getname이 파라미터로 들어온 name하고 같은지 찾아보는것 같은경우에만 필터링 됨
                .findAny(); //하나 찾아지는 것 끝까지 찾았는데 없으면 Optional로 감싸사
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }
}
