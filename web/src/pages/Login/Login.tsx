import React from 'react';
import { LoginForm } from '../../components/Forms/LoginForm';
import SmallHeader from '../../components/SmallHeader';
import { LinkArea } from '../../components/LinkArea';

import * as S from '../../components/GlobalStyle';

export const LoginPage: React.FC = () => {
  return (
    <S.FlexCenterWrap>
      <SmallHeader />
      <LoginForm />
      <S.HorizontalLine />
      <LinkArea />
    </S.FlexCenterWrap>
  );
};
