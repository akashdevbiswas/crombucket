package com.crombucket.storagemanager.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.models.Regions;
import com.crombucket.storagemanager.service.EntityMapper;

@Service
public class EntityMapperImpl implements EntityMapper {

  @Override
  public RegionResponse createRegionResponseFromRegions(Regions regions) {
    return new RegionResponse(
        regions.getId(),
        regions.getRegionName(),
        regions.getRegionCode(),
        regions.getCreatedAt());
  }

  @Override
  public <T, K> PageResponse<T> createPageResponse(Page<K> page, List<T> data) {
    return PageResponse.<T>builder()
    .isFirst(page.isFirst())
    .isLast(page.isLast())
    .page(page.getNumber())
    .size(page.getSize())
    .numberOfElements(page.getNumberOfElements())
    .totalElements(page.getTotalElements())
    .totalPages(page.getTotalPages())
    .content(data)
    .build();
  }

}
