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

export const RegisterForm: React.FC = () => {
  const [formState, setFormState] = useState<RegisterParam>(initialState);
  const handleInputChange = (key: string) => ({
    target: { value },
  }: ChangeEvent<HTMLInputElement>) => {
    setFormState({ ...formState, [key]: value });
  };

  return (
    <S.InputForm autoComplete="off">
      <S.InputContainer>
        <S.Title>Daitnu 계정 만들기</S.Title>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          id="name"
          placeholder="이름"
          value={formState.name}
          onChange={handleInputChange('name')}
        />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.name */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          id="id"
          placeholder="아이디"
          maxLength={20}
          value={formState.id}
          onChange={handleInputChange('id')}
        />
        <S.InputEndText>@daitnu2.com</S.InputEndText>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.id */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.PasswordContainer>
          <S.Input
            id="password"
            type="password"
            placeholder="비밀번호"
            value={formState.password}
            onChange={handleInputChange('password')}
          />
          <S.Input
            id="passwordCheck"
            type="password"
            placeholder="확인"
            value={formState.passwordCheck}
            onChange={handleInputChange('passwordCheck')}
          />
          <S.PasswordVisibleBtn visible={false} />
        </S.PasswordContainer>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.password || errors.checkPassword */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input
          id="email"
          placeholder="이메일"
          value={formState.subEmail}
          onChange={handleInputChange('email')}
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
