package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ //테스트 끝나면 한번 데이터를 지워주는 것
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Van");

        repository.save(member);

        Member resutlt =  repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, resutlt);
        assertThat(member).isEqualTo(resutlt);// 멤버는 REUSLT 와 값이 똑같다 라는 뜻

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //        Member result =  repository.findByName("spring1").get();
    //        assertThat(result).isEqualTo(member1);

        repository.findByName("spring1").ifPresent(result -> assertThat(result).isEqualTo(member1));

    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

         List<Member> result = repository.findAll();

         assertThat(result.size()).isEqualTo(2);


    }

}
