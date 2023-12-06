package com.bc.revan.Entities.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnResponse<T> {
	 
	 boolean isSucces;
	 String errorCode;
	 String errorMessage;
	 T returnValue;
	
		/*public ReturnResponse(T returnValue) {
			super();
			this.isSucces = true;
			this.errorCode = "";
			this.errorMessage = "";
	
			this.returnValue = returnValue;
		}
		public ReturnResponse(boolean isSucces, String errorCode, String errorMessage) {
			super();
			this.isSucces = isSucces;
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
			this.returnValue = null;
		}*/
}
