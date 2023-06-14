package com.gojek.hackathon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "feature_team_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureTeamMapping {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "features")
  @JsonProperty("title")
  private String features;

  @Column(name = "sub_team")
  @JsonProperty("subTeam")
  private String subTeam;

  @Column(name = "issue_type")
  @JsonProperty("issueType")
  private String issueType;

  @Column(name = "team")
  @JsonProperty("team")
  private String team;
}
