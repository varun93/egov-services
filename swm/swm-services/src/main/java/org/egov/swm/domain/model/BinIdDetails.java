package org.egov.swm.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BinIdDetails {

	@Length(min = 1, max = 256)
	@JsonProperty("id")
	private String id = null;

	@NotNull
	@Length(min = 1, max = 128)
	@JsonProperty("tenantId")
	private String tenantId = null;

	@NotNull
	@Size(min = 1, max = 256)
	@JsonProperty("collectionPointId")
	private String collectionPointId = null;

	@Size(min = 5, max = 256)
	@JsonProperty("assetOrBinId")
	private String assetOrBinId = null;

	@JsonProperty("rfidAssigned")
	private Boolean rfidAssigned = null;

	@Size(min = 1, max = 256)
	@JsonProperty("rfid")
	private String rfid = null;

	@JsonProperty("latitude")
	private Double latitude = null;

	@JsonProperty("longitude")
	private Double longitude = null;

}