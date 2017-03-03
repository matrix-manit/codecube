package in.ac.manit.matrix.codecube.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Credential")
@Entity
public class Credential {
	@Id
    @Column(name = "ScholarNo")
	private Long scholarNo;
	
	@Column(name = "Salt")
	private String salt;
}
