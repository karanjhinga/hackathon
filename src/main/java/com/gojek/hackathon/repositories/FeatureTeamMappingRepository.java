package com.gojek.hackathon.repositories;

import com.gojek.hackathon.models.FeatureTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureTeamMappingRepository extends JpaRepository<FeatureTeamMapping, Integer> {

  @Query(value = "SELECT DISTINCT id, team, features, SIMILARITY(features, :searchTerm) AS similarity_score " +
      "FROM feature_team_mapping " +
      "WHERE SIMILARITY(features, :searchTerm) > 0.1 " +
      "ORDER BY similarity_score DESC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findByFeaturesLikeIgnoreCaseOrderBySimilarityScoreDesc(String searchTerm, int limit);

  @Query(value = "SELECT DISTINCT id, team, features, LEVENSHTEIN(features, :searchTerm) " +
      "FROM feature_team_mapping " +
      "ORDER BY LEVENSHTEIN(features, :searchTerm) ASC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findTopByOrderByFeaturesLevenshteinDistanceAsc(String searchTerm, int limit);

  @Query(value = "SELECT DISTINCT id, team, features, WORD_SIMILARITY(features, :searchTerm) AS word_similarity_score " +
      "FROM feature_team_mapping " +
      "WHERE WORD_SIMILARITY(features, :searchTerm) > 0.1 " +
      "ORDER BY word_similarity_score DESC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findByFeaturesWordSimilarityOrderBySimilarityScoreDesc(String searchTerm, int limit);
}
