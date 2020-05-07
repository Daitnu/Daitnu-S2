import styled from 'styled-components';

export const FlexWrap = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
`;

export const FlexColumnWrap = styled.div`
  margin-top: 120px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
`;

export const FlexRowWrap = styled.div`
  height: 100%;
  display: flex;
  flex-direction: row;
  overflow: hidden;
  justify-content: center;
  align-items: center;
`;

export const FlexWidthFullWrap = styled.div`
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  justify-content: center;
  align-items: center;
`;

export const FlexCenterWrap = styled(FlexWrap)`
  justify-content: center;
  align-items: center;
  background-color: #f5f6fa;
`;

export const FlexRowCenterWrap = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const SmallBoard = styled.div`
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #a4b0be;
  border-radius: 15px;
  width: 800px;
`;

export const TinyBoard = styled(SmallBoard)`
  width: 650px;
`;

export const SmallColumnBoard = styled.div`
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 800px;
`;

export const FlexItem = styled(FlexWrap)`
  height: 100%;
  align-items: center;
  justify-content: center;
`;

export const HorizontalLine = styled.hr`
  margin-top: 35px;
  width: 300px;
  color: #bdc3c7;
`;

export const Content = styled.div`
  flex: 1 1;
  overflow: hidden;
  display: grid;
  grid-template-columns: 250px 1fr;
`;

export const AlignLeftContainer = styled.div`
  display: flex;
  width: 60%;
  justify-content: flex-start;
`;

export const BackgroundImgDiv = styled.div`
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
`;
