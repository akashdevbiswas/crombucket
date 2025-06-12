package com.crombucket.storagemanager.models;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Clusters {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(unique = true, name = "cluster_code")
  private String clusterCode;

  @Column(name = "available_space_in_bytes")
  private Long availableSpaceInBytes;

  @CreationTimestamp
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "region_id")
  private Regions regions;

  @OneToMany(mappedBy = "clusters")
  private Set<StorageNodes> storageNodes;
}
