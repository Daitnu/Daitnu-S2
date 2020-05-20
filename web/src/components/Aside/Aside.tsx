import React from 'react';
import * as S from './styled';
import { ResponseCategory } from '~/@types/response/category';

interface AsideProps {
  mobileMenuToggle: boolean;
  itemData: Array<ResponseCategory>;
}

export const Aside: React.FC<AsideProps> = ({ mobileMenuToggle, itemData }) => {
  return (
    <S.Aside mobileMenuToggle={mobileMenuToggle}>
      {itemData.map((item) => (
        <S.AsideItem key={item.id} id={`${item.id}`}>
          {item.name}
        </S.AsideItem>
      ))}
    </S.Aside>
  );
};
