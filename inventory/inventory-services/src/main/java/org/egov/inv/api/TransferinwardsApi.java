/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.egov.inv.api;

import org.egov.inv.model.ErrorRes;
import org.egov.inv.model.RequestInfo;
import org.egov.inv.model.TransferInwardRequest;
import org.egov.inv.model.TransferInwardResponse;

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

@Api(value = "transferinwards", description = "the transferinwards API")
public interface TransferinwardsApi {

    @ApiOperation(value = "Create  new  transfer inward", notes = "Once the approved transfer outward note is received by the indenting store, the indenting store then raises transfer inward note to records material issued by the issuing store. This transfer inward note is linked with the transfer outward note. Once the transfer inward note is approved, the stock information of the indenting store will be increased accordingly. This API is used to record transfer inward in the system.", response = TransferInwardResponse.class, tags={ "TransferInward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferInward created Successfully", response = TransferInwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferinwards/_create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferInwardResponse> transferinwardsCreatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Create  new"  )  @Valid @RequestBody TransferInwardRequest transferInwardRequest);


    @ApiOperation(value = "Get the list of transfer inwards", notes = "Once the approved transfer outward note is received by the indenting store, the indenting store then raises transfer inward note to records material issued by the issuing store. This API isused to search the transfer inwards this raised in the system.", response = TransferInwardResponse.class, tags={ "TransferInward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferInward retrieved Successfully", response = TransferInwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferinwards/_search",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferInwardResponse> transferinwardsSearchPost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "Parameter to carry Request metadata in the request body"  )  @Valid @RequestBody RequestInfo requestInfo, @Size(max=50)@ApiParam(value = "comma seperated list of Ids") @RequestParam(value = "ids", required = false) List<String> ids,@ApiParam(value = "receipt date of the TransferInward ") @RequestParam(value = "receiptDate", required = false) Long receiptDate,@ApiParam(value = "transfer out ward of the TransferInward ") @RequestParam(value = "transferOutWard", required = false) Long transferOutWard,@ApiParam(value = "description of the TransferInward ") @RequestParam(value = "description", required = false) String description,@ApiParam(value = "inward note number of the TransferInward ") @RequestParam(value = "inwardNoteNumber", required = false) String inwardNoteNumber,@ApiParam(value = "inward note status of the TransferInward ", allowableValues = "CREATED, APPROVED, REJECTED, CANCELED") @RequestParam(value = "inwardNoteStatus", required = false) String inwardNoteStatus,@ApiParam(value = "state id of the TransferInward ") @RequestParam(value = "stateId", required = false) Long stateId, @Min(0) @Max(100)@ApiParam(value = "Number of records returned.", defaultValue = "20") @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize,@ApiParam(value = "Page number", defaultValue = "1") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber,@ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords. example name asc,code desc or name,code or name,code desc", defaultValue = "id") @RequestParam(value = "sortBy", required = false, defaultValue="id") String sortBy);


    @ApiOperation(value = "Update any of the transfer inward", notes = "This API is used to update transfer inward information and during the workflow.", response = TransferInwardResponse.class, tags={ "TransferInward", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "TransferInward updated Successfully", response = TransferInwardResponse.class),
        @ApiResponse(code = 400, message = "Invalid Input", response = ErrorRes.class) })
    
    @RequestMapping(value = "/transferinwards/_update",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<TransferInwardResponse> transferinwardsUpdatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,@ApiParam(value = "common Request info"  )  @Valid @RequestBody TransferInwardRequest transferInwardRequest);

}