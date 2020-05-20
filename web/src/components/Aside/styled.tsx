import styled from 'styled-components';
import * as GS from '../GlobalStyle';
import SIZE from '../GlobalStyle/size';
import COLOR from '../GlobalStyle/color';

interface MobileProps {
  mobileMenuToggle: boolean;
}

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
