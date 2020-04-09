package daitnu.daitnus2.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE(400, "COMMON001", "Invalid Input Value"),
  HANDLE_ACCESS_DENIED(403, "COMMON003", "Access is Denied"),
  NOT_FOUND_EXCEPTION(404, "COMMON004", "Page Not Found"),
  METHOD_NOT_ALLOWED(405, "COMMON005", " Method Not Allowed"),
  UNSUPPORTED_MEDIA_TYPE(415,"COMMON015", "Unsupported Media Type"),

  // Auth
  EMAIL_DUPLICATION(400, "AUTH001", "이미 사용중인 이메일 입니다. 다른 이메일을 사용해주세요."),
  ID_DUPLICATION(400, "AUTH002", "이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요."),
  INVALID_LOGIN_INPUT(404, "AUTH003", "아이디 혹은 비밀번호가 올바르지 않습니다."),

  // friend
  USER_NOT_FOUND(404, "FRIEND001", "검색한 아이디는 존재하지 않습니다."),
  USER_ALREADY_FRIEND(400, "FRIEND002", "이미 친구등록한 유저입니다."),
  OWN_SELF_ADD_FRIEND(400, "FRIEND003", "자기자신을 친구추가 할 수 없습니다."),
  FRIEND_SERACH_NOT_FOUND(404, "FRIEND004", "존재하지 않는 유저 입니다."),

  // OneToOne ChatRoom
  ONETOONE_NOT_FOUND(404, "ONETOONEO001", "존재하지 않는 채팅방 입니다."),

  // Message
  CHATROOM_NOT_ACCESS(403, "MESSAGE001", "접근할 수 없는 채팅방입니다."),
  CHATROOM_NOT_FOUND(404, "MESSAGE002", "접근할 수 없는 채팅방입니다."),

  // Mail
  MAIL_NOT_FOUNT(404, "MAIL001", "존재하지 않는 메일입니다."),

  // MailCategory
  INVALID_CATEGORY_NAME(400, "CATEGORY001", "메일함 이름은 완성된 한글, 영문, 숫자로만 이루어질 수 있습니다."),
  DUPLICATED_MAIL_CATEGORY_NAME(400, "CATEGORY002", "같은 이름의 메일함이 이미 존재합니다."),
  CATEGORY_NOT_FOUND(404, "CATEGORY003", "존재하지 않는 메일함입니다."),

  // Mypage
  PASSWORD_DID_NOT_MATCH(400, "MYPAGE001" , "비밀번호가 일치하지 않습니다.")
  ;

  private int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

}
