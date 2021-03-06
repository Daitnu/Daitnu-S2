import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { RootState } from '~/redux';
import { categoryGetRequest } from '~/redux/category/get';
import { isLogin } from '~/library/validate';
import storage from '~/library/storage';
import * as S from './styled';
import { Aside } from '../Aside';

export const Home: React.FC = () => {
  const { userId, userName } = storage.getUserInfo();
  const { loading, data, error } = useSelector((state: RootState) => state.category.get);
  const [mobileMenuToggle, setMobileMenuToggle] = useState(false);
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

  const handleMobileMenuToggle = () => {
    setMobileMenuToggle(!mobileMenuToggle);
  };

  const handleMobileMenuClose = (e: React.MouseEvent<HTMLElement>) => {
    if (mobileMenuToggle) {
      e.stopPropagation();
      setMobileMenuToggle(false);
    }
  };

  return (
    <S.EntireWrapper>
      {mobileMenuToggle && <S.AsideMobileBackground onClick={handleMobileMenuClose} />}
      <S.TopWrapper>
        <S.MobileMenuBtn onClick={handleMobileMenuToggle} />
        Top
      </S.TopWrapper>
      <S.BodyWrapper>
        {data ? <Aside itemData={data.data} mobileMenuToggle={mobileMenuToggle} /> : <S.Loading />}
        <S.Body>하위 {userName} ㅋㅋ</S.Body>
      </S.BodyWrapper>
    </S.EntireWrapper>
  );
};
