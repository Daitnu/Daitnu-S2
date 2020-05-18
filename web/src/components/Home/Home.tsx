import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { RootState } from '~/redux';
import { categoryGetRequest } from '~/redux/category/get';
import { IsLoginParam } from '~/@types/validate';
import * as S from './styled';

const isLogin = ({ user, error }: IsLoginParam) => {
  return user.id !== '' && error?.status !== 401;
};

export const Home: React.FC = () => {
  const user = useSelector((state: RootState) => state.global.user);
  const { loading, data, error } = useSelector((state: RootState) => state.category.get);
  const dispatch = useDispatch();
  const history = useHistory();

  if (!isLogin({ user, error })) {
    history.push('/login');
  }

  useEffect(() => {
    if (isLogin({ user, error })) {
      dispatch(categoryGetRequest());
    }
  }, []);

  if (loading || data === null) {
    return <S.Loading />;
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
