package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookResponse {

    //Class for Add book response Deserialization


    private String msg;
    private String id;

    @JsonProperty("Msg")
    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg=msg;
    }

    @JsonProperty("ID")
    public String getId()
    {
        return id;
    }
    public void setID(String id)
    {
        this.id=id;
    }

}
