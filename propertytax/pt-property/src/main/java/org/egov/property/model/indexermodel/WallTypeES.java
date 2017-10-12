package org.egov.property.model.indexermodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Prasad
 *This class will be used for Elasticseach indexing only
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WallTypeES {
	
	private String code;
	
	private String name;

}