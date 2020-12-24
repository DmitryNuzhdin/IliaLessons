package orm;

import javax.persistence.*;

@Entity
public class TestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "data_column")
    private String data;

    public TestEntity(String data) {
        this.data = data;
    }

    public TestEntity() {
    }

    public long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
