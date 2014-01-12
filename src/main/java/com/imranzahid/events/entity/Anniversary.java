package com.imranzahid.events.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Entity @Access(AccessType.FIELD)
public class Anniversary extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "flower")
  private String flower;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFlower() {
    return flower;
  }

  public void setFlower(String flower) {
    this.flower = flower;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Anniversary)) return false;

    Anniversary that = (Anniversary) o;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "Anniversary{" +
        "id='" + super.getId() + '\'' +
        ", name='" + name + '\'' +
        ", flower='" + flower + '\'' +
        ", active='" + super.isActive() + '\'' +
        '}';
  }
}
