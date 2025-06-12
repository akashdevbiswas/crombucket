package com.crombucket.storagemanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crombucket.storagemanager.models.Clusters;

public interface ClustersRepository extends JpaRepository<Clusters, String>{

  Page<Clusters> findByRegions_IdAndClusterCodeContainingIgnoreCase(String regionId,String clusterCode, Pageable pageable);

}
