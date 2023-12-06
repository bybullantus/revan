package com.bc.revan.Entegration;

import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Base.BaseResponseForObject;

public interface IStatisticsForFixtureScheduledService {
	BaseResponse getStatisticsForFixture() throws InterruptedException, ExecutionException;
}
