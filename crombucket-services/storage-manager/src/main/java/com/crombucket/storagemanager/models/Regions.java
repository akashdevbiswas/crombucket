package com.crombucket.storagemanager.models;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Regions {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(unique = true, name = "region_name")
  private String regionName;
  @Column(unique = true,name = "region_code")
  private String regionCode;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDate createdAt;

  @OneToMany(mappedBy = "regions")
  private Set<Clusters> clusters;
}
