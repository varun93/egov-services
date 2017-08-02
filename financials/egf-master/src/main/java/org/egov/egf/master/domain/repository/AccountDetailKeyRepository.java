package org.egov.egf.master.domain.repository;

import java.util.HashMap;
import java.util.Map;

import org.egov.common.constants.EgfConstants;
import org.egov.common.domain.model.Pagination;
import org.egov.egf.master.domain.model.AccountDetailKey;
import org.egov.egf.master.domain.model.AccountDetailKeySearch;
import org.egov.egf.master.persistence.entity.AccountDetailKeyEntity;
import org.egov.egf.master.persistence.queue.MastersQueueRepository;
import org.egov.egf.master.persistence.repository.AccountDetailKeyJdbcRepository;
import org.egov.egf.master.web.requests.AccountDetailKeyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailKeyRepository {

	@Autowired
	private AccountDetailKeyJdbcRepository accountDetailKeyJdbcRepository;
	@Autowired
	private MastersQueueRepository accountDetailKeyQueueRepository;

	public AccountDetailKey findById(AccountDetailKey accountDetailKey) {
		AccountDetailKeyEntity entity = accountDetailKeyJdbcRepository
				.findById(new AccountDetailKeyEntity().toEntity(accountDetailKey));
		return entity.toDomain();

	}

	@Transactional
	public AccountDetailKey save(AccountDetailKey accountDetailKey) {
		AccountDetailKeyEntity entity = accountDetailKeyJdbcRepository
				.create(new AccountDetailKeyEntity().toEntity(accountDetailKey));
		return entity.toDomain();
	}

	@Transactional
	public AccountDetailKey update(AccountDetailKey accountDetailKey) {
		AccountDetailKeyEntity entity = accountDetailKeyJdbcRepository
				.update(new AccountDetailKeyEntity().toEntity(accountDetailKey));
		return entity.toDomain();
	}

	public void add(AccountDetailKeyRequest request) {
		Map<String, Object> message = new HashMap<>();

		if (request.getRequestInfo().getAction().equalsIgnoreCase(EgfConstants.ACTION_CREATE)) {
			message.put("accountdetailkey_create", request);
		} else {
			message.put("accountdetailkey_update", request);
		}
		accountDetailKeyQueueRepository.add(message);
	}

	public Pagination<AccountDetailKey> search(AccountDetailKeySearch domain) {

		return accountDetailKeyJdbcRepository.search(domain);

	}

}