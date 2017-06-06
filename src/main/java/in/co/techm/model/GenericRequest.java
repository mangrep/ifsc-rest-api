package in.co.techm.model;

import java.io.Serializable;

/**
 * Created by techm on 23/05/17.
 */
public class GenericRequest implements Serializable {
    private String requestStr;

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

    @Override
    public String toString() {
        return "GenericRequest{" +
                "requestStr='" + requestStr + '\'' +
                '}';
    }
}
