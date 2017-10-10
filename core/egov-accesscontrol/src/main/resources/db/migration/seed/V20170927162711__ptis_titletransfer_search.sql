insert into eg_action(id,  name, url, servicecode, queryparams, ordernumber, displayname, enabled, createdby, createddate, lastmodifiedby, lastmodifieddate) values(nextval('SEQ_EG_ACTION'), 'SearchTitleTransfer', '/pt-property/properties/transfer/_search', 'EXIST_PROPERTY', null, 2, 'Search Title Transfer', true, 1, now(), 1, now());
insert into eg_roleaction(roleCode, actionid, tenantId) select code, (select id from eg_action where name='SearchTitleTransfer' and url='/pt-property/properties/transfer/_search'), 'default' from eg_ms_role where code in ('SUPERUSER', 'ULB Operator', 'Property Verifier', 'Property Approver');