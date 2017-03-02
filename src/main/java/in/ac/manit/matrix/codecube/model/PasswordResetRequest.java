package in.ac.manit.matrix.codecube.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
public class PasswordResetRequest {
    @OneToOne
    @JoinColumn(table = "User", referencedColumnName = "ScholarNo")
    private Long scholarNo;

    @Column(name = "RequestTime")
    private Timestamp requestTime;

    @Column(name = "OneTimePassword")
    private String oneTimePassword;

    @Column(name = "TriesRemaining")
    private Integer triesRemaining;


}
