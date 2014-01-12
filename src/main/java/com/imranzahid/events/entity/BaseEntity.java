package com.imranzahid.events.entity;

import javax.persistence.*;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@MappedSuperclass
public class BaseEntity {
  private Long   id;

  @Column(name = "active", length = 1, nullable = false)
  private String active = "Y";

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
      return id;
  }
  public void setId(Long id) {
      this.id = id;
  }

  public boolean isActive() { return "Y".equalsIgnoreCase(active); }

  public void setActive(boolean b) { this.active = (b?"Y":"N"); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof BaseEntity)) return false;

    BaseEntity that = (BaseEntity) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
        "id=" + id +
        '}';
  }
}
