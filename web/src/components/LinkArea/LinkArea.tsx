import React from 'react';

import * as S from './styled';

export const LinkArea: React.FC = () => (
  <S.Footer>
    <a href="help/id">
      <S.TextLink>아이디 찾기</S.TextLink>
    </a>
    <a href="help/password">
      <S.TextLink>비밀번호 찾기</S.TextLink>
    </a>
    <a href="register">
      <S.TextLink>회원가입</S.TextLink>
    </a>
  </S.Footer>
);
