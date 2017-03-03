package in.ac.manit.matrix.codecube.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by spk on 22/9/16.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table (name = "PasswordResetRequest")
@Entity
public class PasswordResetRequest implements Serializable {
    @OneToOne
    @Id
    @JoinColumn(name = "ScholarNo", referencedColumnName = "ScholarNo")
    private User user;

    @Column(name = "RequestTime")
    private Timestamp requestTime;

    @Column(name = "OneTimePassword")
    private String oneTimePassword;

    @Column(name = "TriesRemaining")
    private Integer triesRemaining;
}
