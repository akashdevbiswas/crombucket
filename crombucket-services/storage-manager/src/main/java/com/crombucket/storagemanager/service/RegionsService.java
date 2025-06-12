package com.crombucket.storagemanager.service;

import java.util.List;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.RegionRequest;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

public interface RegionsService {
  PageResponse<RegionResponse> getRegionsBy(Integer page, Integer size, String search,String sortBy, SortDirection direction);

  List<RegionResponse> getAllRegions();

  void saveRegion(RegionRequest regionRequest);
}
