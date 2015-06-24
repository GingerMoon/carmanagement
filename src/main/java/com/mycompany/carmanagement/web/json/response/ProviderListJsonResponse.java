/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.ProviderJsonBean;

import java.util.List;

public class ProviderListJsonResponse {
    
    private String Result;
    
    private List<ProviderJsonBean> Records;
    
    private long TotalRecordCount;
    
    private String Message;

    public ProviderListJsonResponse(String Result, List<ProviderJsonBean> Records, long TotalRecordCount) {
        this.Result = Result;
        this.Records = Records;
        this.TotalRecordCount = TotalRecordCount;
    }

    public ProviderListJsonResponse(String Result, String Message) {
        this.Result = Result;
        this.Message = Message;
    } 

    @JsonProperty("Result")
    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    @JsonProperty("Records")
    public List<ProviderJsonBean> getRecords() {
        return Records;
    }

    public void setRecords(List<ProviderJsonBean> Records) {
        this.Records = Records;
    }

    @JsonProperty("TotalRecordCount")
    public long getTotalRecordCount() {
        return TotalRecordCount;
    }

    public void setTotalRecordCount(int TotalRecordCount) {
        this.TotalRecordCount = TotalRecordCount;
    } 

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }  
}
