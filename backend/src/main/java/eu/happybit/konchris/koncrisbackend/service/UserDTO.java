package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {
    private String address;
    private String phone;

//    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String username;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String photo;

    public UserDTO () {
        // Empty constructor needed for Jackson.
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPhoto () {
        return photo;
    }

    public void setPhoto (String photo) {
        this.photo = photo;
    }

    public UserDTO (User user) {
        this.username = user.getUsername ();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.photo = user.getPhoto ();
        this.phone = user.getPhone ();
        this.address = user.getAddress();
    }

}
