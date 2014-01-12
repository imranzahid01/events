package com.imranzahid.events.entity;

import javax.persistence.*;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@Entity @Access(AccessType.FIELD)
public class User extends BaseEntity {
  @Column(name = "useremail", nullable = false, unique = true)
  private String userEmail;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "firstname", nullable = true)
  private String firstName;

  @Column(name = "lastname", nullable = true)
  private String lastName;

  @Column(name = "api", nullable = false, length = 36, unique = true)
  private String api;

  public User() {}

  public User(String userEmail, String password, String firstName, String lastName, String api) {
    setUserEmail(userEmail);
    setPassword(password);
    setFirstName(firstName);
    setLastName(lastName);
    setApi(api);
  }

  @Transient @SuppressWarnings("unused")
  public String getFullName() {
    if (firstName == null && lastName == null) {
      return userEmail;
    }
    else if (firstName == null) {
      return lastName;
    }
    else if (lastName == null) {
      return firstName;
    }
    else {
      return firstName + " " + lastName;
    }
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getApi() {
    return api;
  }

  public void setApi(String api) {
    this.api = api;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof User)) return false;

    User user = (User) o;

    return userEmail.equals(user.userEmail);
  }

  @Override
  public int hashCode() {
    return userEmail.hashCode();
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + super.getId() + '\'' +
        ", userEmail='" + userEmail + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", active='" + super.isActive() + '\'' +
        '}';
  }
}
