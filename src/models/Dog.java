package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="dogs")
@NamedQueries({
    @NamedQuery(name = "getAllDogs",
            query = "SELECT d FROM Dog AS d ORDER BY d.id DESC"),
    @NamedQuery(name = "getDogsCount",
            query = "SELECT COUNT(d) FROM Dog AS d"),
    @NamedQuery(name = "getMyAllDogs",
            query = "SELECT d FROM Dog AS d WHERE d.user = :user ORDER BY d.id DESC"),
    @NamedQuery(name = "getMyDogsCount",
            query = "SELECT COUNT(d) FROM Dog AS d WHERE d.user = :user")
})

@Entity
public class Dog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "dog_name", nullable = false)
    private String dog_name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "dog_type", nullable = true)
    private String dog_type;

    @Column(name = "age", nullable = true)
    private String age;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Lob
    @Column(name = "image", nullable = true)
    private byte[] image;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
