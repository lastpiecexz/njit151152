package com.itfollowme.materialdesigndemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by notre on 2018/5/31.
 */

public class MeinvResult implements Serializable{
  private boolean error;
  private List<MeinvPhoto> results;
  /**
   * {
   _id: "5b0d6946421aa97f0308836b",
   createdAt: "2018-05-29T22:52:54.29Z",
   desc: "2018-05-31",
   publishedAt: "2018-05-31T00:00:00.0Z",
   source: "web",
   type: "福利",
   url: "http://ww1.sinaimg.cn/large/0065oQSqly1frsllc19gfj30k80tfah5.jpg",
   used: true,
   who: "lijinshanmx"
   },
   */
  public static class MeinvPhoto implements Serializable{
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
      return _id;
    }

    public void set_id(String _id) {
      this._id = _id;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getDesc() {
      return desc;
    }

    public void setDesc(String desc) {
      this.desc = desc;
    }

    public String getPublishedAt() {
      return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
      this.publishedAt = publishedAt;
    }

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isUsed() {
      return used;
    }

    public void setUsed(boolean used) {
      this.used = used;
    }

    public String getWho() {
      return who;
    }

    public void setWho(String who) {
      this.who = who;
    }
  }

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public List<MeinvPhoto> getResults() {
    return results;
  }

  public void setResults(
      List<MeinvPhoto> results) {
    this.results = results;
  }
}
