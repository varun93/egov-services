package org.egov.inv.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contract class for web response. Array of Indent items  are used in case of search ,create or update request.
 */
@ApiModel(description = "Contract class for web response. Array of Indent items  are used in case of search ,create or update request.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-27T06:37:03.617Z")

public class IndentResponse {
    @JsonProperty("responseInfo")
    private ResponseInfo responseInfo = null;

    @JsonProperty("indents")
    private List<Indent> indents = null;

    @JsonProperty("page")
    private Page page = null;

    public IndentResponse responseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
        return this;
    }

    /**
     * Get responseInfo
     *
     * @return responseInfo
     **/
    @ApiModelProperty(value = "")

    @Valid

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public IndentResponse indents(List<Indent> indents) {
        this.indents = indents;
        return this;
    }

    public IndentResponse addIndentsItem(Indent indentsItem) {
        if (this.indents == null) {
            this.indents = new ArrayList<Indent>();
        }
        this.indents.add(indentsItem);
        return this;
    }

    /**
     * Used for search result and create only
     *
     * @return indents
     **/
    @ApiModelProperty(value = "Used for search result and create only")

    @Valid

    public List<Indent> getIndents() {
        return indents;
    }

    public void setIndents(List<Indent> indents) {
        this.indents = indents;
    }

    public IndentResponse page(Page page) {
        this.page = page;
        return this;
    }

    /**
     * Get page
     *
     * @return page
     **/
    @ApiModelProperty(value = "")

    @Valid

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndentResponse indentResponse = (IndentResponse) o;
        return Objects.equals(this.responseInfo, indentResponse.responseInfo) &&
                Objects.equals(this.indents, indentResponse.indents) &&
                Objects.equals(this.page, indentResponse.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseInfo, indents, page);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IndentResponse {\n");

        sb.append("    responseInfo: ").append(toIndentedString(responseInfo)).append("\n");
        sb.append("    indents: ").append(toIndentedString(indents)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
