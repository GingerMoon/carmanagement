/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.ProviderJsonBean;

public class ProviderJsonResponse {
    
    private String Result;
    
    private ProviderJsonBean Records;
    
    private String Message;
    
    public ProviderJsonResponse(){}

    public ProviderJsonResponse(String Result) {
        this.Result = Result;
    }

    public ProviderJsonResponse(String Result, ProviderJsonBean Records) {
        this.Result = Result;
        this.Records = Records;
    }

    public ProviderJsonResponse(String Result, String Message) {
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

    @JsonProperty("Record")
    public ProviderJsonBean getRecords() {
        return Records;
    }

    public void setRecords(ProviderJsonBean Records) {
        this.Records = Records;
    }   

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
