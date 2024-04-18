package lab.inheritance;

import javax.persistence.*;

@MappedSuperclass
public class IdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Basic
    protected String type;

    public IdType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public IdType() {

    }
}
