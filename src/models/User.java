package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "getAllUsers",
                query = "SELECT u FROM User AS u ORDER BY u.id DESC"),
    @NamedQuery(name = "getUsersCount",
                query = "SELECT COUNT(u) FROM User AS u"),
    @NamedQuery(
            name = "checkRegisteredCode",
            query = "SELECT COUNT(u) FROM User AS u WHERE u.code = :code"
        ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT u FROM User AS u WHERE u.code = :code AND u.password = :pass"
        )
})

@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dog_name", nullable = false)
    private String dog_name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "dog_type", nullable = false)
    private String dog_type;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDog_type() {
        return dog_type;
    }

    public void setDog_type(String dog_type) {
        this.dog_type = dog_type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}
