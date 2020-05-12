import styled from 'styled-components';
import * as GS from '../GlobalStyle';

export const EntireWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 100%;
  flex-direction: column;
`;

export const TopWrapper = styled(GS.FlexCenter)`
  width: 100%;
  height: 15%;
  border: 1px solid black;
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
`;

export const Body = styled(GS.FlexCenter)`
  width: 80%;
  height: 100%;
  border: 1px solid black;
`;