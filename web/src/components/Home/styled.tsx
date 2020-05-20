import styled from 'styled-components';
import * as GS from '../GlobalStyle';
import LoadingGif from '~/assets/gif/loading.gif';
import MobileMenuBtnPng from '~/assets/png/mobile_menu_btn.png';
import COLOR from '../GlobalStyle/color';
import SIZE from '../GlobalStyle/size';

interface MobileProps {
  mobileMenuToggle: boolean;
}

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

export const Aside = styled(GS.FlexCenter)<MobileProps>`
  width: 20%;
  height: 100%;
  flex-direction: column;
  @media (max-width: 732px) {
    position: absolute;
    background-color: white;
    width: ${SIZE.MOBILE.ASIDE.WIDTH};
    height: 100%;
    top: 0;
    left: -${SIZE.MOBILE.ASIDE.WIDTH};
    transition: transform 1s;
    transform: translateX(
      ${({ mobileMenuToggle }) => (mobileMenuToggle ? SIZE.MOBILE.ASIDE.WIDTH : '0px')}
    );
    z-index: 2;
  }
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
  @media (max-width: 732px) {
    width: 100%;
  }
`;

export const Loading = styled(GS.BackgroundImgDiv)`
  width: 50px;
  height: 50px;
  background-image: url(${LoadingGif});
`;
