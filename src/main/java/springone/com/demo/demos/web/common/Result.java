package springone.com.demo.demos.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }



    public Result(String message) {
        this.message = message;
    }

    public static Result success(){
        return new Result(200);
    }

    public static Result fail(int code,String message){
        return new Result<String>(code,message);
    }

    public static <T>Result<T> success(T data){
        return new Result(200,data);
    }


}
