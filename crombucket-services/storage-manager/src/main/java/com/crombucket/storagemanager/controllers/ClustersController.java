package com.crombucket.storagemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.ClusterRequest;
import com.crombucket.storagemanager.dtos.response.ClustersResponse;
import com.crombucket.storagemanager.service.ClustersService;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/clusters")
@RequiredArgsConstructor
public class ClustersController {

  private final ClustersService clustersService;

  @PostMapping(value = "/{regionId}")
  public void createCluster(@PathVariable String regionId, @RequestBody ClusterRequest clusterRequest) {
    clustersService.saveCluster(regionId, clusterRequest);
  }

  @GetMapping(value = "/{regionId}")
  public ResponseEntity<PageResponse<ClustersResponse>> findClusters(
      @PathVariable String regionId,
      @RequestParam(required = false, defaultValue = "0") Integer page,
      @RequestParam(required = false, defaultValue = "6") Integer size,
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "availableSpaceInBytes") String sortBy,
      @RequestParam(required = false, defaultValue = "ASC") SortDirection direction) {
    return ResponseEntity.ok().body(clustersService.getClustersBy(regionId, page, size, search, sortBy, direction));
  }

  @PutMapping(value = "/{clusterId}")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public void updateCluster(@PathVariable String clusterId, @RequestBody ClusterRequest clusterRequest) {
    clustersService.updateCluster(clusterId, clusterRequest);
  }

}
