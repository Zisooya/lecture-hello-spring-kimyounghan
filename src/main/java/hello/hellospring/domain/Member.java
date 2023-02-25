package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    // 데이터를 구분하기 위한 시스템 저장용 id
    //IDENTITY : DB가 알아서 아이디값을 생성해주는 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
