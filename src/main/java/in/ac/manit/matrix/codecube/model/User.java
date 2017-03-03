package in.ac.manit.matrix.codecube.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.constants.UserConstants.TableColumns;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * This class acts as a model to map the User Table into the object
 * of desired type.
 *
 * @author Mohit Godwani
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Table(name = "User")
@Entity
public class User implements Serializable {
    @Id
    @Column(name = TableColumns.scholarNumber)
    private Long scholarNumber;

    @Column(name = TableColumns.name)
    private String name;

    @Column(name = TableColumns.emailAddress)
    private String emailAddress;

    @Column(name = TableColumns.gender, columnDefinition = "enum('Male','Female')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = TableColumns.phoneNumber)
    private String phoneNumber;

    @Column(name = TableColumns.handle)
    private String handle;

    @Column(name = TableColumns.branch)
    private String branch;

    @Column(name = TableColumns.yearRating)
    private double yearRating;

    @Column(name = TableColumns.overallRating)
    private double overallRating;

    @Column(name = TableColumns.joiningDate)
    private Date joiningDate;
}
