package com.crombucket.storagemanager.service;

import java.util.List;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.RegionRequest;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.utils.SortOrder;

public interface RegionsService {
  PageResponse<RegionResponse> getRegionsBy(Integer page, Integer size, String search, SortOrder sort);

  List<RegionResponse> getAllRegions();

  void saveRegion(RegionRequest regionRequest);
}
