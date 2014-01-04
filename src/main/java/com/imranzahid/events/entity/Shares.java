package com.imranzahid.events.entity;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Imran Zahid
 *         date 12/29/13
 */
@Entity @Access(AccessType.FIELD)
public class Shares extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "id", insertable = false, updatable = false)
  private User user;

  @Column(name = "guid", nullable = false, length = 36)
  private String guid;

  @Column(name = "sname", nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(name = "sharecategories",
      joinColumns = {
          @JoinColumn(name = "sid", referencedColumnName = "id", insertable = false, updatable = false)
      },
      inverseJoinColumns = {
          @JoinColumn(name = "cid", referencedColumnName = "id", insertable = false, updatable = false)
      }
  )
  private Set<Category> categories = Sets.newHashSet();

  @ManyToMany
  @JoinTable(name = "sharerelationships",
      joinColumns = {
          @JoinColumn(name = "sid", referencedColumnName = "id", insertable = false, updatable = false)
      },
      inverseJoinColumns = {
          @JoinColumn(name = "rid", referencedColumnName = "id", insertable = false, updatable = false)
      }
  )
  private Set<Relationship> relations = Sets.newHashSet();

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getName() {
    return name;
  }

  public void setName(String sname) {
    this.name = sname;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public Set<Relationship> getRelations() {
    return relations;
  }

  public void setRelations(Set<Relationship> relations) {
    this.relations = relations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Shares)) return false;

    Shares that = (Shares) o;

    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "Shares{" +
        "sname='" + name + '\'' +
        ", categories='" + categories + '\'' +
        ", relations='" + relations + '\'' +
        '}';
  }
}
