import React from 'react';
import S from './styled';
import logo from '../../assets/logo/logo.png';

const SmallHeader: React.FC = () => {
  return (
    <a href="/">
      <S.Brand>
        <S.Logo src={logo} />
        <S.Title>Daitnu</S.Title>
      </S.Brand>
    </a>
  );
};
export default SmallHeader;
