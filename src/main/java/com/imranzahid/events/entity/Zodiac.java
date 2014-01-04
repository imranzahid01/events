package com.imranzahid.events.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Entity @Access(AccessType.FIELD)
public class Zodiac extends BaseEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start", nullable = false)
  private Date start;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end", nullable = false)
  private Date end;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Zodiac)) return false;

    Zodiac that = (Zodiac) o;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "Zodiac{" +
        "name='" + name + '\'' +
        '}';
  }
}
