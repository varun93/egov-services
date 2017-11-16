/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.egov.inv.api;

import org.egov.inv.model.ErrorRes;
import org.egov.inv.model.RequestInfo;
import org.egov.inv.model.TransferOutwardRequest;
import org.egov.inv.model.TransferOutwardResponse;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "org.egov.inv.codegen.languages.SpringCodegen", date = "2017-11-08T13:51:07.770Z")

@Api(value = "transferoutwards", description = "the transferoutwards API")
public interface TransferoutwardsApi {

    @ApiOperation(value = "Create new transfer outward note.", notes = "Once the approved transfer indent is received by the issuing store, the issuing store initiated the transfer outward process to issue the material required by the indent store. This API is invoked to record the Transfer Outward note raised by the issuing store. Once the transfer outward note is approved, inventory information of materials for issuing store will be reduced accordingly.", response = TransferOutwardResponse.class, tags={ "TransferOutward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferOutward created Successfully", response = TransferOutwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferoutwards/_create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferOutwardResponse> transferoutwardsCreatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Create  new"  )  @Valid @RequestBody TransferOutwardRequest transferOutwardRequest);


    @ApiOperation(value = "Get the list of transfer outwards.", notes = "Once the approved transfer indent is received by the issuing store, the issuing store initiated the transfer outward process to issue the material required by the indent store. This API is used to search Transfer Outward notes raised in the system.", response = TransferOutwardResponse.class, tags={ "TransferOutward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferOutward retrieved Successfully", response = TransferOutwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferoutwards/_search",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferOutwardResponse> transferoutwardsSearchPost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Parameter to carry Request metadata in the request body"  )  @Valid @RequestBody RequestInfo requestInfo, @Size(max=50)@ApiParam(value = "comma seperated list of Ids") @RequestParam(value = "ids", required = false) List<String> ids,@ApiParam(value = "store of the TransferOutward ") @RequestParam(value = "store", required = false) Long store,@ApiParam(value = "issue date of the TransferOutward ") @RequestParam(value = "issueDate", required = false) Long issueDate,@ApiParam(value = "issued to employee of the TransferOutward ") @RequestParam(value = "issuedToEmployee", required = false) String issuedToEmployee,@ApiParam(value = "description of the TransferOutward ") @RequestParam(value = "description", required = false) String description,@ApiParam(value = "outward note number of the TransferOutward ") @RequestParam(value = "outwardNoteNumber", required = false) String outwardNoteNumber,@ApiParam(value = "outward note status of the TransferOutward ", allowableValues = "CREATED, APPROVED, REJECTED, CANCELED") @RequestParam(value = "outwardNoteStatus", required = false) String outwardNoteStatus,@ApiParam(value = "indent of the TransferOutward ") @RequestParam(value = "indent", required = false) Long indent,@ApiParam(value = "state id of the TransferOutward ") @RequestParam(value = "stateId", required = false) Long stateId, @Min(0) @Max(100)@ApiParam(value = "Number of records returned.", defaultValue = "20") @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize,@ApiParam(value = "Page number", defaultValue = "1") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber,@ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords. example name asc,code desc or name,code or name,code desc", defaultValue = "id") @RequestParam(value = "sortBy", required = false, defaultValue="id") String sortBy);


    @ApiOperation(value = "Update any of the transfer outwards", notes = "This API is invoked to update the transfer outward note information and during the workflow.", response = TransferOutwardResponse.class, tags={ "TransferOutward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferOutward updated Successfully", response = TransferOutwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferoutwards/_update",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferOutwardResponse> transferoutwardsUpdatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "common Request info"  )  @Valid @RequestBody TransferOutwardRequest transferOutwardRequest);

}