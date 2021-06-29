package models;

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
        @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User AS u ORDER BY u.id DESC"),
        @NamedQuery(name = "getUsersCount", query = "SELECT COUNT(u) FROM User AS u"),
        @NamedQuery(name = "checkRegisteredCode", query = "SELECT COUNT(u) FROM User AS u WHERE u.code = :code"),
        @NamedQuery(name = "checkLoginCodeAndPassword", query = "SELECT u FROM User AS u WHERE u.code = :code AND u.password = :pass")
})

@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


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
