import React, { useState } from 'react';
import S from './styled';

interface ILoginState {
  userId: string;
  password: string;
}

const loginInitState: ILoginState = {
  userId: '',
  password: '',
};

export const LoginForm: React.FC = () => {
  const [loginState, setLoginState] = useState<ILoginState>(loginInitState);

  const handleOnChange = (key: string) => ({ target: { value } }): void =>
    setLoginState({ ...loginState, [key]: value }); // noImplicitAny옵션을 설정 안하면 value에서 빨간줄

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
        value={loginState.userId}
        onChange={handleOnChange('userId')}
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
        value={loginState.password}
        onChange={handleOnChange('password')}
      />
      <S.ErrorText></S.ErrorText>
      <S.Button className="submit-btn max-width">로그인</S.Button>
    </S.InputForm>
  );
};
