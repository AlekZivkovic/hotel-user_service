package raf.sk.user.service.user.service.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Admin extends  User{


    public Admin() {
    }

    public Admin(String username, String password, String email, Credentials credentials, Role role) {
        super(username, password, email, credentials, role);
    }


}
