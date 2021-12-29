package raf.sk.user.service.user.service.domain;


import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class Credentials {
    private String name;
    private String lastName;
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Credentials() {
    }

    public Credentials(String name, String lastName, String phoneNumber, Date birthday) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
