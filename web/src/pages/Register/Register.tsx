import React from 'react';
import SmallHeader from '~/components/SmallHeader';
import { RegisterForm } from '~/components/Forms/RegisterForm';
import { LinkArea } from '~/components/LinkArea';

import * as S from '../../components/GlobalStyle';

export const RegisterPage: React.FC = () => {
  return (
    <S.FlexCenterWrap>
      <SmallHeader />
      <RegisterForm />
      <S.HorizontalLine />
      <LinkArea />
    </S.FlexCenterWrap>
  );
};
