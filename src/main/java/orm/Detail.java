package orm;

import javax.persistence.*;

@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn
    private Master master;

    public Detail(String name, Master master) {
        this.name = name;
        this.master = master;
    }

    public Detail() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Master getMaster() {
        return master;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", master=" + master +
                '}';
    }
}
