package com.panvdev.apirest_prueba.respuestas;

public class ApiResponse {
	private String message;
    private boolean result;
    private Object data;

    

    public static ApiResponse success(String message, Object data) {
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setResult(true);
        response.setData(data);
        return response;
    }

    public static ApiResponse error(String message) {
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setResult(false);
        return response;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiResponse() {
	}
    
    
}
