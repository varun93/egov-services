package org.egov.lcms.repository;

import java.util.ArrayList;
import java.util.List;

import org.egov.lcms.config.PropertiesManager;
import org.egov.lcms.models.Advocate;
import org.egov.lcms.models.AdvocateDetails;
import org.egov.lcms.models.AdvocateSearchCriteria;
import org.egov.lcms.models.Case;
import org.egov.lcms.models.CaseSearchCriteria;
import org.egov.lcms.models.CaseVoucher;
import org.egov.lcms.models.HearingDetails;
import org.egov.lcms.models.ParaWiseComment;
import org.egov.lcms.repository.builder.CaseBuilder;
import org.egov.lcms.repository.rowmapper.AdvocateDetailsRowMapper;
import org.egov.lcms.repository.rowmapper.CaseRowMapper;
import org.egov.lcms.repository.rowmapper.CaseVoucherRowMapper;
import org.egov.lcms.repository.rowmapper.HearingDetailsRowMapper;
import org.egov.lcms.repository.rowmapper.ParaWiseRowMapper;
import org.egov.lcms.utility.ConstantUtility;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Prasad
 *
 */
@Repository
@Slf4j
public class CaseSearchRepository {

	@Autowired
	CaseBuilder caseBuilder;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CaseRowMapper caseRowMapper;

	@Autowired
	ParaWiseRowMapper paraWiseRowMapper;

	@Autowired
	HearingDetailsRowMapper hearingDetailsRowMapper;

	@Autowired
	AdvocateDetailsRowMapper advocateDetailsRowMapper;

	@Autowired
	CaseVoucherRowMapper caseVoucherRowMapper;

	@Autowired
	PropertiesManager propertiesManager;
	
	@Autowired
	AdvocateRepository advocateRepository;
	

	/**
	 * This will search the cases based on the given casesearchCriteria
	 * 
	 * @param caseSearchCriteria
	 * @return
	 */
	public List<Case> searchCases(CaseSearchCriteria caseSearchCriteria) {

		final List<Object> preparedStatementValues = new ArrayList<Object>();
		final String queryStr = caseBuilder.getQuery(caseSearchCriteria, preparedStatementValues);

		List<Case> cases = new ArrayList<Case>();
		try {
			cases = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), caseRowMapper);
		} catch (final Exception exception) {
			log.info("the exception in case search :" + exception);
			throw new CustomException(propertiesManager.getCaseResponseErrorCode(), exception.getMessage());
		}

		for (Case casee : cases) {
			casee.setParawiseComments(searchParaWiseComments(casee));
			casee.setHearingDetails(searchHearingDetails(casee));
			casee.setAdvocateDetails(searchAdvocateDetails(casee));
			casee.setCaseVoucher(searchCaseVoucher(casee));
		}

		return cases;
	}

	private List<ParaWiseComment> searchParaWiseComments(Case casee) {
		List<ParaWiseComment> paraWiseComments = new ArrayList<ParaWiseComment>();
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		final String queryStr = caseBuilder.searchByCaseCodeQuery(casee, ConstantUtility.PARAWISE_COMMENTS_TABLE_NAME,
				Boolean.TRUE, preparedStatementValues);

		try {
			paraWiseComments = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), paraWiseRowMapper);
		} catch (Exception ex) {
			throw new CustomException(propertiesManager.getParaWiseResponseErrorCode(), ex.getMessage());
		}
		return paraWiseComments;
	}

	private List<HearingDetails> searchHearingDetails(Case casee) {
		List<HearingDetails> hearingDetails = new ArrayList<HearingDetails>();
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		final String queryStr = caseBuilder.searchByCaseCodeQuery(casee, ConstantUtility.HEARING_DETAILS_TABLE_NAME,
				Boolean.TRUE, preparedStatementValues);

		try {
			hearingDetails = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), hearingDetailsRowMapper);
		} catch (Exception ex) {
			throw new CustomException(propertiesManager.getHearingDetailsResponseErrorCode(), ex.getMessage());
		}
		return hearingDetails;
	}

	/**
	 * 
	 * @param casee
	 * @return 
	 */
	private List<AdvocateDetails> searchAdvocateDetails(Case casee) {
		List<AdvocateDetails> advocateDetails = new ArrayList<>();
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		final String queryStr = caseBuilder.searchByCaseCodeQuery(casee, ConstantUtility.ADVOCATE_DETAILS_TABLE_NAME,
				Boolean.FALSE, preparedStatementValues);

		try {
			advocateDetails = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), advocateDetailsRowMapper);
		} catch (Exception ex) {
			throw new CustomException(propertiesManager.getAdvocateDetailsResponseErrorCode(), ex.getMessage());
		}
		setAdvocate(advocateDetails);
		
		return advocateDetails;
	}

	/**
	 * This Api will set the advocate details based on the given advocateDetails
	 * @param advocateDetails
	 */
	private void setAdvocate(List<AdvocateDetails> advocateDetails) {
		
		for ( AdvocateDetails advocateDetail : advocateDetails ){
			AdvocateSearchCriteria advocateSearchCriteria = new AdvocateSearchCriteria();
			advocateSearchCriteria.setTenantId(advocateDetail.getTenantId());
			if ( advocateDetail.getAdvocate().getCode()!=null && !advocateDetail.getAdvocate().getCode().isEmpty()){
				advocateSearchCriteria.setCode(new String [] {advocateDetail.getAdvocate().getCode()});
				List<Advocate> advocates = advocateRepository.search(advocateSearchCriteria);
				if ( advocates!=null && advocates.size()>0)
				advocateDetail.setAdvocate(advocates.get(0));
				
			}
			
			
			
		}
		
		
	}

	private CaseVoucher searchCaseVoucher(Case casee) {
		List<CaseVoucher> caseVouchers = new ArrayList<>();
		final List<Object> preparedStatementValues = new ArrayList<Object>();
		final String queryStr = caseBuilder.searchByCaseCodeQuery(casee, ConstantUtility.CASE_VOUCHER_TABLE_NAME,
				Boolean.TRUE, preparedStatementValues);

		try {
			caseVouchers = jdbcTemplate.query(queryStr, preparedStatementValues.toArray(), caseVoucherRowMapper);
		} catch (Exception ex) {
			throw new CustomException(propertiesManager.getCaseVoucherResponseErrorCode(), ex.getMessage());
		}
		if (caseVouchers != null && caseVouchers.size() > 0)
			return caseVouchers.get(0);

		return null;
	}
}
