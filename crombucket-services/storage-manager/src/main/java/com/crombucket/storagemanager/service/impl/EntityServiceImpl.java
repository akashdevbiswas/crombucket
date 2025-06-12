package com.crombucket.storagemanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.ClusterRequest;
import com.crombucket.storagemanager.dtos.requests.RegionRequest;
import com.crombucket.storagemanager.dtos.response.ClustersResponse;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.exceptions.RegionNotFoundException;
import com.crombucket.storagemanager.models.Clusters;
import com.crombucket.storagemanager.models.Regions;
import com.crombucket.storagemanager.repository.ClustersRepository;
import com.crombucket.storagemanager.repository.RegionsRepository;
import com.crombucket.storagemanager.service.ClustersService;
import com.crombucket.storagemanager.service.EntityMapper;
import com.crombucket.storagemanager.service.RegionsService;
import com.crombucket.storagemanager.utils.SortOrders;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EntityServiceImpl implements RegionsService, ClustersService {

  private final EntityMapper entityMapper;
  private final RegionsRepository regionsRepository;
  private final ClustersRepository clustersRepository;

  @Override
  public PageResponse<RegionResponse> getRegionsBy(Integer page, Integer size, String search, String sortBy, SortDirection direction) {

    Pageable pageable = PageRequest.of(page, size, SortOrders.getOrders(sortBy, direction));

    Page<Regions> regionPage = null;

    if (search == null || search.isBlank()) {
      regionPage = regionsRepository.findAll(pageable);
    } else {
      regionPage = regionsRepository.findByRegionNameOrRegionCodeContainingIgnoreCase(search, search, pageable);
    }
    List<RegionResponse> regionsData = regionPage.getContent().stream()
        .map(entityMapper::createRegionResponseFromRegions).toList();

    return entityMapper.createPageResponse(regionPage, regionsData);
  }

  @Override
  public List<RegionResponse> getAllRegions() {
    return regionsRepository.findAll().stream().map(entityMapper::createRegionResponseFromRegions).toList();
  }

  @Override
  public void saveRegion(RegionRequest regionRequest) {
    Regions regions = Regions.builder()
        .regionName(regionRequest.regionName())
        .regionCode(regionRequest.regionCode().toUpperCase())
        .build();
    regionsRepository.save(regions);
  }

  @Override
  public void saveCluster(String regionId, ClusterRequest clusterRequest) {
    Optional<Regions> regionsOptional = regionsRepository.findById(regionId);

    Regions regions = regionsOptional.orElseThrow(() -> {
      String message = String.format("Region with id %s not found", regionId);
      return new RegionNotFoundException(message);
    });

    Clusters clusters = Clusters.builder()
        .clusterCode(clusterRequest.clusterCode())
        .availableSpaceInBytes(0L)
        .regions(regions)
        .build();

    clustersRepository.save(clusters);
  }

  @Override
  public PageResponse<ClustersResponse> getClustersBy(String regionCode, Integer page, Integer size, String search, String sortBy, SortDirection direction) {

    Sort sortOrders = SortOrders.getOrders(sortBy, direction);

    Pageable pageable = PageRequest.of(page,size,sortOrders);

    Page<Clusters> clustersPage = clustersRepository.findByRegions_IdAndClusterCodeContainingIgnoreCase(regionCode, search, pageable);

    List<ClustersResponse> clustersData = clustersPage.getContent().stream()
        .map(entityMapper::createClustersResponseFromClusters).toList();

    return entityMapper.createPageResponse(clustersPage, clustersData);
  }

  @Override
  public void updateCluster(String clusterId, ClusterRequest clusterRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateCluster'");
  }

}
