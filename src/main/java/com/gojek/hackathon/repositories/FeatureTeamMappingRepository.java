package com.gojek.hackathon.repositories;

import com.gojek.hackathon.models.FeatureTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureTeamMappingRepository extends JpaRepository<FeatureTeamMapping, Integer> {

  @Query(value = "SELECT id, team, features, issue_type, sub_team, SIMILARITY(features, :searchTerm) AS similarity_score " +
      "FROM feature_team_mapping " +
      "WHERE SIMILARITY(features, :searchTerm) > 0.1 " +
      "ORDER BY similarity_score DESC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findByFeaturesLikeIgnoreCaseOrderBySimilarityScoreDesc(String searchTerm, int limit);

  @Query(value = "SELECT id, team, features, issue_type, sub_team, LEVENSHTEIN(features, :searchTerm) AS levenshtein_score " +
      "FROM (SELECT DISTINCT id, team, features, issue_type, sub_team FROM feature_team_mapping) AS subquery " +
      "ORDER BY levenshtein_score ASC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findTopByOrderByFeaturesLevenshteinDistanceAsc(String searchTerm, int limit);

  @Query(value = "SELECT id, team, features, issue_type, sub_team, WORD_SIMILARITY(features, :searchTerm) AS word_similarity_score " +
      "FROM feature_team_mapping " +
      "WHERE WORD_SIMILARITY(features, :searchTerm) > 0.1 " +
      "ORDER BY word_similarity_score DESC " +
      "LIMIT :limit", nativeQuery = true)
  List<FeatureTeamMapping> findByFeaturesWordSimilarityOrderBySimilarityScoreDesc(String searchTerm, int limit);
}
