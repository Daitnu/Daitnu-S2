import React, { useState } from 'react';
import S from './styled';
import { RootState } from '~/redux';
import { useSelector, useDispatch } from 'react-redux';
import { loginRequest } from '~/redux/user/login';
import { validate, ID, PASSWORD } from '~/library/validate';
import { LoginParam } from '~/@types/request/user';

const loginInitState: LoginParam = {
  [ID]: '',
  [PASSWORD]: '',
};

export const LoginForm: React.FC = () => {
  const [loginState, setLoginState] = useState<LoginParam>(loginInitState);
  const [errState, setErrState] = useState<LoginParam>(loginInitState);
  const { loading, error } = useSelector((state: RootState) => state.user.login);
  const dispatch = useDispatch();

  const handleOnChange = (key: string) => ({ target: { value } }) => {
    setLoginState({ ...loginState, [key]: value });
    setErrState({ ...errState, [key]: validate({ key, value }) });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (loading) {
      return;
    }
    setErrState({ ...loginInitState });

    const errResult: LoginParam = {
      [ID]: validate({ key: [ID], value: loginState[ID] }),
      [PASSWORD]: validate({ key: [PASSWORD], value: loginState[PASSWORD] }),
    };

    if (Object.values(errResult).some((v) => v !== '')) {
      setErrState({ ...errResult });
      return;
    }

    dispatch(loginRequest({ [ID]: loginState[ID], [PASSWORD]: loginState[PASSWORD] }));
  };

  if (loading) {
    return <S.Loading />;
  }

  return (
    <S.InputForm autoComplete="off">
      <S.Input
        type="text"
        placeholder="아이디"
        maxLength={20}
        value={loginState[ID]}
        onChange={handleOnChange(ID)}
      />
      <S.ErrorText>{errState[ID]}</S.ErrorText>
      <S.Input
        type="password"
        placeholder="비밀번호"
        maxLength={30}
        value={loginState[PASSWORD]}
        onChange={handleOnChange(PASSWORD)}
      />
      <S.ErrorText>{errState[PASSWORD] || (error && error.message)}</S.ErrorText>
      <S.Button onClick={handleSubmit}>로그인</S.Button>
    </S.InputForm>
  );
};
