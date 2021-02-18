package zyd.datacenter.Payload;

public class Result {
    String message;
    int code; // 0代表失败，1代表成功，-1代表异常

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
