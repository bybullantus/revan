package com.bc.revan.Entegration;

import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.Base.BaseResponse;

public interface ILineupForFixtureScheduledService {
	BaseResponse getLineups() throws InterruptedException, ExecutionException;
}
