package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HelloSpringApplicationTests {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach  //테스트 하나 끝낼 때 마다 각각 돈다
	public void afterEach(){
		repository.clearStore(); //테스트는 의존관계 없이 각각 돌아야해서 한 테스트 끝날 때마다 비워줘야 한다
	}

	@Test
	public void save(){
		Member member = new Member();
		member.setName("spring"); //ctrl+shift+enter : 바로 줄바꿈

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
		// Assertions.assertEquals(member,result); //import org.junit.jupiter.api.Assertions;
		assertThat(member).isEqualTo(result); // import org.assertj.core.api.

	}

	@Test
	public void findByName(){
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring1").get();

		assertThat(result).isEqualTo(member1);
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
