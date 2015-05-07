package weChat.core.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class CommonController {

	 private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ErrorAttributes errorAttributes;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex,
			HttpServletRequest request) {
		logger.error("程序出错", ex);
		Map<String, Object> body = getErrorAttributes(request,
				getTraceParameter(request));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}

	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equals(parameter.toLowerCase());
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(
				request);
		return this.errorAttributes.getErrorAttributes(requestAttributes,
				includeStackTrace);
	}
}
