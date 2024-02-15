package hello.startspring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 entity
public class Member {

    // PK설정, identity(DB가 알아서 생성해주는 건 identity, sequence)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column(name = "username") // db에서 name을 username으로 mapping해준다.
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
