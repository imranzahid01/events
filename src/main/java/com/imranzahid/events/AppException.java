package com.imranzahid.events;

/**
 * @author Imran Zahid
 *         date 12/28/13
 */
@SuppressWarnings("serial")
public class AppException extends RuntimeException {
  private Enum errorEnum;

  public AppException(Enum errorEnum){
    this.errorEnum = errorEnum;
  }

  public Enum getErrorEnum(){
    return this.errorEnum;
  }

  public String getErrorCode(){
    return this.errorEnum.name();
  }
}
