import styled from 'styled-components';
import * as GS from '../GlobalStyle';
import LoadingGif from '~/assets/gif/loading.gif';
import MobileMenuBtnPng from '~/assets/png/mobile_menu_btn.png';

export const EntireWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 100%;
  flex-direction: column;
`;

export const TopWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 15%;
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
`;

export const AsideMobileBackground = styled.div`
  @media (max-width: 732px) {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: grey;
    opacity: 0.5;
    z-index: 1;
  }
`;

export const Body = styled(GS.FlexCenter)`
  width: 80%;
  height: 100%;
  @media (max-width: 732px) {
    width: 100%;
  }
`;

export const Loading = styled(GS.BackgroundImgDiv)`
  width: 50px;
  height: 50px;
  background-image: url(${LoadingGif});
`;
