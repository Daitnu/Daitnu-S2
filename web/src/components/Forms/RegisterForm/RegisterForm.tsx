import React from 'react';
import S from './styled';

export const RegisterForm: React.FC = () => {
  return (
    <S.InputForm autoComplete="off">
      <S.InputContainer>
        <S.Title>Daitnu 계정 만들기</S.Title>
      </S.InputContainer>
      <S.InputContainer>
        <input id="name" />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.name */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <input id="id" />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.id */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <input id="password" />
        <input id="passwordCheck" />
        <button>아이콘</button>
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.password || errors.checkPassword */}</S.ErrorText>
      </S.InputContainer>
      <S.InputContainer>
        <input id="email" />
      </S.InputContainer>
      <S.InputContainer>
        <S.ErrorText>{/* errors.register || errors.email */}</S.ErrorText>
      </S.InputContainer>
      <S.Button>가입하기</S.Button>
    </S.InputForm>
  );
};
