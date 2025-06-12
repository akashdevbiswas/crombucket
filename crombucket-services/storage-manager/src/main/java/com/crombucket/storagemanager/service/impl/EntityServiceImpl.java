package com.crombucket.storagemanager.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.RegionRequest;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.models.Regions;
import com.crombucket.storagemanager.repository.RegionsRepository;
import com.crombucket.storagemanager.service.EntityMapper;
import com.crombucket.storagemanager.service.RegionsService;
import com.crombucket.storagemanager.utils.SortOrder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements RegionsService {

  private final EntityMapper entityMapper;
  private final RegionsRepository regionsRepository;

  @Override
  public PageResponse<RegionResponse> getRegionsBy(Integer page, Integer size, String search, SortOrder sort) {

    Pageable pageable = PageRequest.of(page, size, sort.getSort("regionName"));

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
    .regionCode(regionRequest.regionCode())
    .build();
    regionsRepository.save(regions);
  }

}
