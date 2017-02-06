package org.egov.workflow.controller;

import java.util.Date;

import org.egov.workflow.model.AssigneeFilterInfo;
import org.egov.workflow.model.AssigneeRequestInfo;
import org.egov.workflow.model.PositionResponse;
import org.egov.workflow.model.ResponseInfo;
import org.egov.workflow.service.PgrWorkflowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkFlowController {

    @Autowired
    private PgrWorkflowImpl pgrWorkflowImpl;
    
    @PostMapping(value = "/assignee", produces = MediaType.APPLICATION_JSON_VALUE)
    public PositionResponse getComplaintAssignee(@RequestBody final AssigneeRequestInfo request) {
        final AssigneeFilterInfo assigneeFilterInfo = request.getAssigneeFilterInfo();

        final PositionResponse positionResponse = pgrWorkflowImpl.getAssignee(assigneeFilterInfo.getBoundaryId(),
                assigneeFilterInfo.getComplaintTypeCode(), assigneeFilterInfo.getCurrentAssigneeId());
        if (positionResponse != null)
            positionResponse.setResponseInfo(
                    new ResponseInfo("", "", new Date().toString(), "", "", "Successful response", ""));
        return positionResponse;
    }

}
