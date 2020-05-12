import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '~/redux';
import { useHistory } from 'react-router-dom';
import * as S from './styled';

export const Home: React.FC = () => {
  const user = useSelector((state: RootState) => state.user);
  const history = useHistory();
  if (user.id === '') {
    history.push('/login');
  }
  return (
    <S.EntireWrapper>
      <S.TopWrapper>Top</S.TopWrapper>
      <S.BodyWrapper>
        <S.Aside>Aside</S.Aside>
        <S.Body>하위 {user.name} ㅋㅋ</S.Body>
      </S.BodyWrapper>
    </S.EntireWrapper>
  );
};
