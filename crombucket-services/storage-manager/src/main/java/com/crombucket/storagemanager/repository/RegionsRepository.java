package com.crombucket.storagemanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crombucket.storagemanager.models.Regions;

public interface RegionsRepository extends JpaRepository<Regions,String> {


  Page<Regions> findByRegionNameOrRegionCodeContainingIgnoreCase(String regionName, String regionCode , Pageable pageable);

}
