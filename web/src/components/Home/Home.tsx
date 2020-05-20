import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { RootState } from '~/redux';
import { categoryGetRequest } from '~/redux/category/get';
import { isLogin } from '~/library/validate';
import storage from '~/library/storage';
import * as S from './styled';

export const Home: React.FC = () => {
  const { userId, userName } = storage.getUserInfo();
  const { loading, data, error } = useSelector((state: RootState) => state.category.get);
  const dispatch = useDispatch();
  const history = useHistory();

  if (!isLogin({ userId, userName })) {
    history.push('/login');
  }

  useEffect(() => {
    if (isLogin({ userId, userName })) {
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
        <S.Aside>
          {data ? (
            data.data.map((v) => (
              <S.AsideItem key={v.id} id={`${v.id}`}>
                {v.name}
              </S.AsideItem>
            ))
          ) : (
            <S.Loading />
          )}
        </S.Aside>
        <S.Body>하위 {userName} ㅋㅋ</S.Body>
      </S.BodyWrapper>
    </S.EntireWrapper>
  );
};
