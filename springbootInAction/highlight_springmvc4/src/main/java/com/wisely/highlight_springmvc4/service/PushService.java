package com.wisely.highlight_springmvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ClassName PushService
 * @USER haifei
 * @DATE 27/8/2018 10:18 AM
 * @Description
 */
@Service
public class PushService {

  private DeferredResult<String> deferredResult;

  public DeferredResult<String> getAsyncUPdate() {
    deferredResult = new DeferredResult<>();
    return deferredResult;
  }

  @Scheduled(fixedDelay = 5000)
  public void refresh() {
    if (deferredResult != null) {
      deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
    }
  }
}
