package com.itfollowme.baidufanyi.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by notre on 2018/5/24.
 */

public class FanyiResponse implements Serializable {
  /**
   * {
   from: "en",
   to: "zh",
   trans_result: [
   {
   src: "apple",
   dst: "苹果"
   }
   ]
   }
   */
  private String from;
  private String to;
  private List<TransResult> transResult;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public List<TransResult> getTransResult() {
    return transResult;
  }

  public void setTransResult(
      List<TransResult> transResult) {
    this.transResult = transResult;
  }

  public static class TransResult implements Serializable {
    private String desc;
    private String dst;

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public String getDst() {
      return dst;
    }

    public void setDst(String dst) {
      this.dst = dst;
    }
  }
}
