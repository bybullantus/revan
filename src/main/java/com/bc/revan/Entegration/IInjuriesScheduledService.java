package com.bc.revan.Entegration;

import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.Base.BaseResponse;

public interface IInjuriesScheduledService {
	BaseResponse getInjuries() throws InterruptedException, ExecutionException;
}
