package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //- IDENTITY전략 - 오라클은 시퀀스 테이블에서 채번 PK 직접넣어줄 수 있고,. 하지만 DB가 알아서 생성해주는 것을 IDENTITY라고 한다,.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


// jpa 객체 - ORM: 오브젝트 릴레이셔널 매핑