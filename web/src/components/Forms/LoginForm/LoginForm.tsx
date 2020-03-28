import React from 'react';
import S from './styled';

export const LoginForm: React.FC = () => {
  return (
    <S.InputForm>
      <S.Input
        type="text"
        className="form-control"
        id="userId"
        placeholder="아이디"
        name="userId"
        maxLength={20}
        autoComplete="off"
      />
      <S.ErrorText></S.ErrorText>
      <S.Input
        type="password"
        className="form-control"
        id="password"
        placeholder="비밀번호"
        name="password"
        maxLength={20}
        autoComplete="off"
      />
      <S.ErrorText></S.ErrorText>
      <S.Button className="submit-btn max-width">로그인</S.Button>
    </S.InputForm>
  );
};
