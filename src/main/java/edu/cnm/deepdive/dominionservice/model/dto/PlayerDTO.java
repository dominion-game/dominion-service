package edu.cnm.deepdive.dominionservice.model.dto;

public class PlayerDTO {
  private String screenName;
  private String loginEmail;

  public PlayerDTO(String screenName, String loginEmail) {
    this.screenName = screenName;
    this.loginEmail = loginEmail;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public String getLoginEmail() {
    return loginEmail;
  }

  public void setLoginEmail(String loginEmail) {
    this.loginEmail = loginEmail;
  }
}
