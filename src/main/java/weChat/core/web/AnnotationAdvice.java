package weChat.core.web;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import weChat.utils.RespMsgCode;

//Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
public class AnnotationAdvice {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handlerRuntimeException(Exception ex,
			WebRequest request) {
		String message = ex.getMessage();
		int code = RespMsgCode.SERVER_ERROR;
		ErrorMsg errorMsg = new ErrorMsg(code, message);
		return handleExceptionInternal(errorMsg);
	}
	/**
	 * 参数校验出错
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidException(MethodArgumentNotValidException ex, WebRequest request){
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> errorList = bindingResult.getAllErrors();
		String msg = "";
		for(ObjectError objectError : errorList){
			if(objectError instanceof FieldError){
				FieldError fieldError = (FieldError) objectError;
				msg = fieldError.getField() + ":" + fieldError.getDefaultMessage();
			}else{
				msg = objectError.getObjectName() +":" +  objectError.getDefaultMessage();
			}
		}
		int code = RespMsgCode.ARGUMENT_NOT_VALID;
		ErrorMsg errorMsg = new ErrorMsg(code, msg);
		return handleExceptionInternal(errorMsg);
		
	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Object body) {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
	}
}
