import React, { useState, ChangeEvent } from 'react';
import S from './styled';
import { RegisterParam } from '~/@types/request/user';

const initialState: RegisterParam = {
  id: '',
  password: '',
  passwordCheck: '',
  name: '',
  subEmail: '',
};

const ID = 'id' as const;
const PASSWORD = 'password' as const;
const PASSWORD_CHECK = 'passwordCheck' as const;
const NAME = 'name' as const;
const SUB_EMAIL = 'subEmail' as const;

export const RegisterForm: React.FC = () => {
  const [formState, setFormState] = useState<RegisterParam>(initialState);
  const [passwordVisible, setPasswordVisible] = useState(false);

  const handleInputChange = (key: string) => ({
    target: { value },
  }: ChangeEvent<HTMLInputElement>) => {
    setFormState({ ...formState, [key]: value });
  };

  const handlePasswordVisible = () => {
    setPasswordVisible(!passwordVisible);
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
      <S.Button>가입하기</S.Button>
      <S.Button>아이디가 있으신가요? 로그인하러 가시죠!</S.Button>
    </S.InputForm>
  );
};
