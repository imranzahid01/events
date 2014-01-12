package com.imranzahid.events.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Entity @Access(AccessType.FIELD)
public class Event extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "edate", nullable = false)
  private Date date;

  @Column(name = "ename", nullable = false)
  private String name;

  @ManyToOne @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
  private Category category;

  @ManyToOne @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
  private Relationship relationship;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String ename) {
    this.name = ename;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Relationship getRelationship() {
    return relationship;
  }

  public void setRelationship(Relationship relationship) {
    this.relationship = relationship;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Event)) return false;

    Event that = (Event) o;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "Event{" +
        "id='" + super.getId() + '\'' +
        ", name='" + name + '\'' +
        ", date=" + date +
        ", active='" + super.isActive() + '\'' +
        '}';
  }
}
