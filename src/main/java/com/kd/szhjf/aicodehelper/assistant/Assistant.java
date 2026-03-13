package com.kd.szhjf.aicodehelper.assistant;

import reactor.core.publisher.Flux;

/**
 * 聊天
 */
interface Assistant {

  /**
   * 聊天 - 流式返回
   * @param message
   * @return
   */
  Flux<String> chat(String message);
}
