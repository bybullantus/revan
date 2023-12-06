package com.bc.revan.Entegration;

import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.Base.BaseResponse;

public interface IStandingScheduledService {
	BaseResponse getStanding()  throws InterruptedException, ExecutionException;
}
