import React, { useState, ChangeEvent } from 'react';
import { useHistory } from 'react-router';
import S from './styled';
import { RegisterParam } from '~/@types/request/user';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '~/redux';
import { registerRequest } from '~/redux/user/register';

const ID = 'id' as const;
const PASSWORD = 'password' as const;
const PASSWORD_CHECK = 'passwordCheck' as const;
const NAME = 'name' as const;
const SUB_EMAIL = 'subEmail' as const;

const initialState: RegisterParam = {
  [ID]: '',
  [PASSWORD]: '',
  [PASSWORD_CHECK]: '',
  [NAME]: '',
  [SUB_EMAIL]: '',
};

export const RegisterForm: React.FC = () => {
  const history = useHistory();
  const [formState, setFormState] = useState<RegisterParam>(initialState);
  const [passwordVisible, setPasswordVisible] = useState(false);
  const { loading, data, error } = useSelector((state: RootState) => state.userRegister);
  const dispatch = useDispatch();

  const handleInputChange = (key: string) => ({
    target: { value },
  }: ChangeEvent<HTMLInputElement>) => {
    setFormState({ ...formState, [key]: value });
  };

  const handlePasswordVisible = () => {
    setPasswordVisible(!passwordVisible);
  };

  const handleRegister = (e) => {
    // TODO: Validation
    e.preventDefault();
    if (loading) {
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
        <S.Input placeholder="이름" value={formState.name} onChange={handleInputChange(NAME)} />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.name */}</S.ErrorText>
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
        <S.ErrorText>{/* errors.id */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.PasswordContainer>
          <S.Input
            type={passwordVisible ? 'text' : PASSWORD}
            placeholder="비밀번호"
            value={formState.password}
            onChange={handleInputChange(PASSWORD)}
          />
          <S.Input
            type={passwordVisible ? 'text' : PASSWORD}
            placeholder="확인"
            value={formState.passwordCheck}
            onChange={handleInputChange(PASSWORD_CHECK)}
          />
          <S.PasswordVisibleBtn visible={passwordVisible} onClick={handlePasswordVisible} />
        </S.PasswordContainer>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.password || errors.checkPassword */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          placeholder="이메일"
          value={formState.subEmail}
          onChange={handleInputChange(SUB_EMAIL)}
        />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.register || errors.email */}</S.ErrorText>
      </S.InputContainer>
      <S.Button requesting={loading} onClick={handleRegister}>
        {loading ? '잠시만 기다려주세요' : '가입하기'}
      </S.Button>
      <S.Button onClick={() => history.push('/login')}>
        아이디가 있으신가요? 로그인하러 가시죠!
      </S.Button>
    </S.InputForm>
  );
};
