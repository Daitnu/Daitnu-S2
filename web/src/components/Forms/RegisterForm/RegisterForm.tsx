import React, { useState, ChangeEvent, useEffect } from 'react';
import { useHistory } from 'react-router';
import S from './styled';
import { RegisterParam } from '~/@types/request/user';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '~/redux';
import { registerRequest, registerClear } from '~/redux/user/register';
import {
  validate,
  equalValidate,
  ID,
  PASSWORD,
  PASSWORD_CHECK,
  NAME,
  SUB_EMAIL,
} from '~/library/validate';

const initialState: RegisterParam = {
  [ID]: '',
  [PASSWORD]: '',
  [PASSWORD_CHECK]: '',
  [NAME]: '',
  [SUB_EMAIL]: '',
};

const getErr = ({ key, value, formState }) => {
  const { password } = formState;
  if (key === PASSWORD_CHECK) return equalValidate(password, value);
  else return validate({ key, value });
};

export const RegisterForm: React.FC = () => {
  const history = useHistory();
  const [formState, setFormState] = useState<RegisterParam>(initialState);
  const [formErrState, setFormErrState] = useState<RegisterParam>(initialState);
  const [passwordVisible, setPasswordVisible] = useState(false);
  const { loading, error } = useSelector((state: RootState) => state.user.register);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(registerClear());
  }, []);

  const handleInputChange = (key: string) => ({
    target: { value },
  }: ChangeEvent<HTMLInputElement>) => {
    setFormState({ ...formState, [key]: value });
    setFormErrState({
      ...formErrState,
      [key]: getErr({ key, value, formState }),
    });
  };

  const handlePasswordVisible = () => {
    setPasswordVisible(!passwordVisible);
  };

  const handleRegister = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault();
    if (loading) {
      return;
    }
    setFormErrState({ ...initialState });

    const { id, name, password, passwordCheck, subEmail } = formState;
    const errResult: RegisterParam = {
      [ID]: getErr({ key: ID, value: id, formState }),
      [PASSWORD]: getErr({ key: PASSWORD, value: password, formState }),
      [PASSWORD_CHECK]: getErr({ key: PASSWORD_CHECK, value: passwordCheck, formState }),
      [NAME]: getErr({ key: NAME, value: name, formState }),
      [SUB_EMAIL]: getErr({ key: SUB_EMAIL, value: subEmail, formState }),
    };

    if (Object.values(errResult).some((v) => v !== '')) {
      setFormErrState({ ...errResult });
      return;
    }

    dispatch(registerRequest({ ...formState }));
  };

  return (
    <S.InputForm autoComplete="off">
      <S.InputContainer>
        <S.Title>Daitnu 계정 만들기</S.Title>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          placeholder="이름"
          maxLength={10}
          value={formState.name}
          onChange={handleInputChange(NAME)}
        />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{formErrState.name}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          placeholder="아이디"
          maxLength={20}
          value={formState.id}
          onChange={handleInputChange(ID)}
        />
        <S.InputEndText>@daitnu2.com</S.InputEndText>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{formErrState.id}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.PasswordContainer>
          <S.Input
            type={passwordVisible ? 'text' : PASSWORD}
            placeholder="비밀번호"
            value={formState.password}
            maxLength={30}
            onChange={handleInputChange(PASSWORD)}
          />
          <S.Input
            type={passwordVisible ? 'text' : PASSWORD}
            placeholder="확인"
            value={formState.passwordCheck}
            maxLength={30}
            onChange={handleInputChange(PASSWORD_CHECK)}
          />
          <S.PasswordVisibleBtn visible={passwordVisible} onClick={handlePasswordVisible} />
        </S.PasswordContainer>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{formErrState.password || formErrState.passwordCheck}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          placeholder="이메일"
          value={formState.subEmail}
          onChange={handleInputChange(SUB_EMAIL)}
          maxLength={50}
        />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{formErrState.subEmail || (error && error.message)}</S.ErrorText>
      </S.InputContainer>
      <S.Button disabled={loading} requesting={loading} onClick={handleRegister}>
        {loading ? '잠시만 기다려주세요' : '가입하기'}
      </S.Button>
      <S.Button onClick={() => history.push('/login')}>
        아이디가 있으신가요? 로그인하러 가시죠!
      </S.Button>
    </S.InputForm>
  );
};
