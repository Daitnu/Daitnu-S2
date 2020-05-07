import React from 'react';
import { Link } from 'react-router-dom';

import * as S from './styled';

export const LinkArea: React.FC = () => {
  return (
    <S.Footer>
      <Link to="help/id">
        <S.TextLink>아이디 찾기</S.TextLink>
      </Link>
      <Link to="help/password">
        <S.TextLink>비밀번호 찾기</S.TextLink>
      </Link>
      <Link to="register">
        <S.TextLink>회원가입</S.TextLink>
      </Link>
    </S.Footer>
  );
};
