package com.gojek.hackathon.controllers;

import com.gojek.hackathon.models.FeatureTeamMapping;
import com.gojek.hackathon.services.FeatureTeamMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api")
@CrossOrigin(maxAge = 3600)
public class FeatureTeamMappingController {
  private final FeatureTeamMappingService service;

  @Autowired
  public FeatureTeamMappingController(FeatureTeamMappingService service) {
    this.service = service;
  }

  @GetMapping("/fuzzy-search/similarity")
  public List<FeatureTeamMapping> fuzzySearchByTrigram(@RequestParam("q") String searchTerm,
                                                       @RequestParam(value = "limit", defaultValue = "5") int limit) {
    List<FeatureTeamMapping> featureTeamMappings = service.fuzzySearchByTrigram(searchTerm, limit);
    return featureTeamMappings;
  }

  @GetMapping("/fuzzy-search/levenshtein-distance")
  public List<FeatureTeamMapping> findClosestMatches(@RequestParam("q") String searchTerm,
                                                     @RequestParam(value = "limit", defaultValue = "5") int limit) {
    List<FeatureTeamMapping> closestMatches = service.findClosestMatches(searchTerm, limit);
    return closestMatches;
  }

  @GetMapping("/fuzzy-search/word-similarity")
  public List<FeatureTeamMapping> wordSimilaritySearch(@RequestParam("q") String searchTerm,
                                                       @RequestParam(value = "limit", defaultValue = "5") int limit) {
    List<FeatureTeamMapping> featureTeamMappings = service.wordSimilaritySearch(searchTerm, limit);
    return featureTeamMappings;
  }
}
