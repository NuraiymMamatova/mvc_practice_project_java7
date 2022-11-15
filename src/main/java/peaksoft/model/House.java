package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class House {

    @Id
    @SequenceGenerator(name = "seq.gen", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq.gen")
    private Long id;

    private String name;

    private String image;

    private int price;

    private String description;

    private String address;

    private int person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);

    }
}
