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

  @Column(name = "active", length = 1, nullable = false)
  private String active;

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

  public boolean isActive() { return "Y".equalsIgnoreCase(active); }

  public void setActive(boolean b) { this.active = (b?"Y":"N"); }

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
        "name='" + name + '\'' +
        ", flower='" + flower + '\'' +
        ", active='" + active + '\'' +
        '}';
  }
}
