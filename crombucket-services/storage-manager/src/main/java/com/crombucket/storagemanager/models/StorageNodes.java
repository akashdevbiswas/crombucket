package com.crombucket.storagemanager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageNodes {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String storageNodeId;

  @ManyToOne
  @JoinColumn(name = "cluster_id")
  private Clusters clusters;
}
