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

  @Column(name = "active", length = 1, nullable = false)
  private String active;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isActive() { return "Y".equalsIgnoreCase(active); }

  public void setActive(boolean b) { this.active = (b?"Y":"N"); }

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
        "name='" + name + '\'' +
        '}';
  }
}
