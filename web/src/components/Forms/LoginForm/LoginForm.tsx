import React, { useState, useEffect } from 'react';
import S from './styled';
import { RootState } from '~/redux';
import { useSelector, useDispatch } from 'react-redux';
import { loginRequest } from '~/redux/user/login';

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
  const { loading, data, error } = useSelector((state: RootState) => state.userLogin);
  const { id, email, name } = useSelector((state: RootState) => state.user);
  const dispatch = useDispatch();

  const handleOnChange = (key: string) => ({ target: { value } }): void =>
    setLoginState({ ...loginState, [key]: value }); // noImplicitAny옵션을 설정 안하면 value에서 빨간줄

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(loginRequest({ id: loginState.userId, password: loginState.password }));
  };

  // TODO: 삭제
  useEffect(() => {
    console.log(data);
    console.log(error);
    console.log({ id, email, name });
    // dispatch(
    //   registerRequest({
    //     id: 'idid123',
    //     password: '12341234',
    //     passwordCheck: '12341234',
    //     name: 'namegg',
    //     subEmail: 'idid123@daitnu.com',
    //   }),
    // );
  }, [data, error, id, name, email]);

  if (loading) {
    return <S.Loading />;
  }

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
