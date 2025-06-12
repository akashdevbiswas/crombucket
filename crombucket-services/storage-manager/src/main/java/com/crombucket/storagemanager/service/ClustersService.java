package com.crombucket.storagemanager.service;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.ClusterRequest;
import com.crombucket.storagemanager.dtos.response.ClustersResponse;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

public interface ClustersService {

  void saveCluster(String regionId, ClusterRequest clusterRequest);

  PageResponse<ClustersResponse> getClustersBy(String regionId, Integer page, Integer size, String search, String sortBy,SortDirection direction);

  void updateCluster(String clusterId, ClusterRequest clusterRequest);

}
