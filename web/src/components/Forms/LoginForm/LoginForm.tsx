import React, { useState } from 'react';
import S from './styled';
import { Api } from '~/library/request/Api';

interface ILoginState {
  userId: string;
  password: string;
}

const loginInitState: ILoginState = {
  userId: '',
  password: '',
};

interface ResponseType {
  id: number;
  name: string;
}

interface RequestType {
  d: string;
}

export const LoginForm: React.FC = () => {
  const [loginState, setLoginState] = useState<ILoginState>(loginInitState);

  const handleOnChange = (key: string) => ({ target: { value } }): void =>
    setLoginState({ ...loginState, [key]: value }); // noImplicitAny옵션을 설정 안하면 value에서 빨간줄

  const handleSubmit = async (e) => {
    e.preventDefault();

    const api = new Api();
    const response1 = await api.get<ResponseType>({
      url: '/mail/category',
    });
    console.log(response1);
    const response2 = await api.get<ResponseType, RequestType>({
      url: '/mail/category',
      data: { d: '123' },
    });
    console.log(response2);
  };

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
      <S.Button className="submit-btn max-width" onClick={handleSubmit}>
        로그인
      </S.Button>
    </S.InputForm>
  );
};
