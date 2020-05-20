import styled from 'styled-components';
import * as GS from '../GlobalStyle';
import LoadingGif from '~/assets/gif/loading.gif';
import MobileMenuBtnPng from '~/assets/png/mobile_menu_btn.png';
import COLOR from '../GlobalStyle/color';

export const EntireWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 100%;
  flex-direction: column;
`;

export const TopWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 15%;
  border: 1px solid black;
  position: relative;
`;

export const MobileMenuBtn = styled(GS.BackgroundImgDiv)`
  @media (max-width: 732px) {
    top: 5px;
    left: 5px;
    width: 40px;
    height: 40px;
    position: absolute;
    cursor: pointer;
    background-image: url(${MobileMenuBtnPng});
  }
`;

export const BodyWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 85%;
  border: 1px solid black;
`;

export const Aside = styled(GS.FlexCenter)`
  width: 20%;
  height: 100%;
  border: 1px solid black;
  flex-direction: column;
  @media (max-width: 732px) {
    display: none;
  }
`;

export const AsideItem = styled(GS.FlexCenter)`
  width: 100%;
  height: 3rem;
  font-size: 1.2rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  cursor: pointer;
  color: ${COLOR.MAIN};
  &:hover {
    background-color: ${COLOR.GREY};
    font-weight: 700;
  }
`;

export const Body = styled(GS.FlexCenter)`
  width: 80%;
  height: 100%;
  border: 1px solid black;
  @media (max-width: 732px) {
    width: 100%;
  }
`;

export const Loading = styled(GS.BackgroundImgDiv)`
  width: 50px;
  height: 50px;
  background-image: url(${LoadingGif});
`;
