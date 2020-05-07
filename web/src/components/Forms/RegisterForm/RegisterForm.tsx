import React from 'react';
import S from './styled';

export const RegisterForm: React.FC = () => {
  return (
    <S.InputForm autoComplete="off">
      <S.InputContainer>
        <S.Title>Daitnu 계정 만들기</S.Title>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input id="name" placeholder="이름" />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.name */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input id="id" placeholder="아이디" maxLength={20} />
        <S.InputEndText>@daitnu2.com</S.InputEndText>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.id */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.PasswordContainer>
          <S.Input id="password" type="password" placeholder="비밀번호" />
          <S.Input id="passwordCheck" type="password" placeholder="확인" />
          <S.PasswordVisibleBtn visible={false} />
        </S.PasswordContainer>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.password || errors.checkPassword */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <S.Input id="email" placeholder="이메일" />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.register || errors.email */}</S.ErrorText>
      </S.InputContainer>
      <S.Button>가입하기</S.Button>
      <S.Button>아이디가 있으신가요? 로그인하러 가시죠!</S.Button>
    </S.InputForm>
  );
};
