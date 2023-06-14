package com.gojek.hackathon.services;

import com.gojek.hackathon.models.FeatureTeamMapping;
import com.gojek.hackathon.repositories.FeatureTeamMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureTeamMappingService {
  private final FeatureTeamMappingRepository repository;

  @Autowired
  public FeatureTeamMappingService(FeatureTeamMappingRepository repository) {
    this.repository = repository;
  }

  public List<FeatureTeamMapping> fuzzySearchByTrigram(String searchTerm, int limit) {
    List<FeatureTeamMapping> result = repository.findByFeaturesLikeIgnoreCaseOrderBySimilarityScoreDesc(searchTerm, limit);
    return result;
  }

  public List<FeatureTeamMapping> findClosestMatches(String searchTerm, int limit) {
    List<FeatureTeamMapping> result = repository.findTopByOrderByFeaturesLevenshteinDistanceAsc(searchTerm, limit);
    return result;
  }

  public List<FeatureTeamMapping> wordSimilaritySearch(String searchTerm, int limit) {
    List<FeatureTeamMapping> result = repository.findByFeaturesWordSimilarityOrderBySimilarityScoreDesc(searchTerm, limit);
    return result;
  }
}
