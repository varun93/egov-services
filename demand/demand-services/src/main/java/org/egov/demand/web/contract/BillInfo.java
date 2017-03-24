/*
 * eGov Demand
 * eGov Demand manages head wise demand and Demand Bill information for all the revenue modules from eGov solution. It manages * The demand data for a revenue module with category of tax for a defined *period, rebate, penalty, late payment interest, fee* etc and again head wise in each category. This module have one entity called Demand for every revenue entity which holds the list of category and head wise demands for a defined period in DemandDetail. * The Demand Bill data with period and head wise dues and its payment details.
 *
 * OpenAPI spec version: 0.0.0
 * Contact: ramakrishna@egovernments.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.egov.demand.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BillInfo {
	private Long id = null;
	private Long demandId = null;
	private String citizenName = null;
	private String citizenAddress = null;
	private String billNumber = null;
	private String billType = null;
	private Date issuedDate = null;
	private Date lastDate = null;
	private String moduleName = null;
	private String createdBy = null;
	private String history = null;
	private String cancelled = null;
	private String fundCode = null;
	private Long functionaryCode = null;
	private String fundSourceCode = null;
	private String departmentCode = null;
	private String collModesNotAllowed = null;
	private Integer boundaryNumber = null;
	private String boundaryType = null;
	private Double billAmount = null;
	private Double billAmountCollected = null;
	private String serviceCode = null;
	private Character partPaymentAllowed = null;
	private Character overrideAccHeadAllowed = null;
	private String description = null;
	private Double minAmountPayable=0d;
	private String consumerCode = null;
	private String displayMessage = null;
	private Character callbackForApportion = null;
	private String emailId = null;
	private String consumerType = null;
	private Double totalAmount = null;
	private List<BillDetailInfo> billDetailInfos = new ArrayList<BillDetailInfo>();
}
