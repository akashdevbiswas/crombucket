package com.crombucket.storagemanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.ClusterRequest;
import com.crombucket.storagemanager.dtos.response.ClustersResponse;
import com.crombucket.storagemanager.models.Regions;
import com.crombucket.storagemanager.repository.RegionsRepository;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

// @SpringBootTest
public class ClustersServiceTest {

  @Autowired
  private ClustersService clustersService;
  
  @Autowired
  private RegionsRepository regionsRepository;

  private Regions regionsKol;
  private Regions regionsDelhi;

  // @BeforeEach
  void setUpNewRegions(){
    Regions regKol = Regions.builder()
    .regionCode("IND-01-KOL")
    .regionName("Kolkata India")
    .build();

    Regions regDel  = Regions.builder()
    .regionCode("IND-01-DEL")
    .regionName("New Delhi India")
    .build();
    this.regionsKol = regionsRepository.save(regKol);
    this.regionsDelhi = regionsRepository.save(regDel);
  }

  // @Test
  void createClusterForRegion() {
    List<ClusterRequest> clustersListKol = List.of(new ClusterRequest("CLS8801"), new ClusterRequest("CLS8802"));
    List<ClusterRequest> clusterListDelhi = List.of(new ClusterRequest("CLS9901"), new ClusterRequest("CLS9902"), new ClusterRequest("CLS9903"));
    
    clustersService.saveCluster(this.regionsKol.getRegionCode(), clustersListKol.get(0));
    clustersService.saveCluster(this.regionsKol.getRegionCode(), clustersListKol.get(1));
    clustersService.saveCluster(this.regionsDelhi.getRegionCode(), clusterListDelhi.get(0));
    clustersService.saveCluster(this.regionsDelhi.getRegionCode(), clusterListDelhi.get(1));
    clustersService.saveCluster(this.regionsDelhi.getRegionCode(), clusterListDelhi.get(2));


    PageResponse<ClustersResponse> clusterForKol = clustersService.getClustersBy(regionsKol.getId(), 0, 10, "", "availableSpaceInBytes", SortDirection.ASC);
    PageResponse<ClustersResponse> clusterForDelhi = clustersService.getClustersBy(regionsDelhi.getId(), 0, 10, "", "availableSpaceInBytes", SortDirection.ASC);
    

    assertEquals(clusterListDelhi.size(), clusterForDelhi.getContent().size());
    assertEquals(clustersListKol.size(), clusterForKol.getContent().size());

  }

}
