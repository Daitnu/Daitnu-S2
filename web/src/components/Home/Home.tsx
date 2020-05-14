import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { RootState } from '~/redux';
import { categoryGetRequest } from '~/redux/category/get';
import * as S from './styled';

export const Home: React.FC = () => {
  const user = useSelector((state: RootState) => state.global.user);
  const { loading, data, error } = useSelector((state: RootState) => state.category.get);
  const dispatch = useDispatch();
  const history = useHistory();

  if (user.id === '') {
    history.push('/login');
  }

  useEffect(() => {
    if (user.id !== '') {
      dispatch(categoryGetRequest());
    }
  }, []);

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
