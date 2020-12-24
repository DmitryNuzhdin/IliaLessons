package orm;

import javax.persistence.*;
import java.util.List;

@Entity
public class Master {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    public Master(String name) {
        this.name = name;
    }

    public Master() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "master")
    List<Detail> detailList;

    public List<Detail> getDetailList() {
        return detailList;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
