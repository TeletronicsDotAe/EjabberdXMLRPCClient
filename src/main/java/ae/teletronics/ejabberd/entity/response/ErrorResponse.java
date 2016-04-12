package ae.teletronics.ejabberd.entity.response;

/**
 * Created by kristian on 4/7/16.
 */
public class ErrorResponse {
    String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hasError(){
        return this.error != null;
    }

}
