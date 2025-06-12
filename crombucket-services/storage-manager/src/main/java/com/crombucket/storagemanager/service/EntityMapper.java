package com.crombucket.storagemanager.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.models.Regions;

public interface EntityMapper {

  RegionResponse createRegionResponseFromRegions(Regions regions);

  <T,K> PageResponse<T> createPageResponse (Page <K> pageable, List<T> data );
}
