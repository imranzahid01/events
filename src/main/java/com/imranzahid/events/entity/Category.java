package com.imranzahid.events.entity;

import javax.persistence.*;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Entity @Access(AccessType.FIELD)
public class Category extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Category)) return false;

    Category that = (Category) o;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "Category{" +
        "id='" + super.getId() + '\'' +
        ", name='" + name + '\'' +
        ", active='" + super.isActive() + '\'' +
        '}';
  }
}
