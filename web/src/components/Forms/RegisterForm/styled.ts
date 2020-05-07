import styled from 'styled-components';
import { BackgroundImgDiv } from '~/components/GlobalStyle';
import VisibleIcon from '~/assets/png/visible.png';
import InVisibleIcon from '~/assets/png/inVisible.png';
import COLOR from '~/components/GlobalStyle/color';

interface InputProps {
  fontSize?: number;
}

interface VisibleIconProps {
  visible: boolean;
}

interface ButtonProps {
  requesting?: boolean;
}

const InputForm = styled.form`
  width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 40px;
`;

const InputContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const InputEndText = styled.span`
  position: absolute;
  right: 10px;
  color: ${COLOR.DEEP_GREY};
  user-select: none;
`;

const Input = styled.input<InputProps>`
  width: 100%;
  height: ${({ fontSize }) => (fontSize ? `${fontSize * 2.7}px` : '40px')};
  font-size: ${({ fontSize }) => (fontSize ? `${fontSize}px` : '15px')};
  padding: 0 ${({ fontSize }) => (fontSize ? `${fontSize * 0.7}px` : '10px')};
  border: 1px solid ${COLOR.GREY};
  border-radius: 5px;
  outline: none;
  &:focus {
    border-color: ${COLOR.MAIN};
  }
`;

const PasswordContainer = styled.div`
  width: 100%;
  display: grid;
  grid-template-columns: 35% 35% 10%;
  justify-content: space-between;
`;

const Button = styled.button<ButtonProps>`
  width: 100%;
  height: 40px;
  background-color: ${COLOR.MAIN};
  color: white;
  box-sizing: border-box;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 10px 0 auto;
  border: 0;
  cursor: ${({ requesting }) => !requesting && 'pointer'};
  opacity: ${({ requesting }) => requesting && '0.4'};
`;

const ErrorText = styled.span`
  width: 100%;
  color: red;
  margin-left: 10px;
  margin-bottom: 15px;
  padding: 0;
  font-size: 0.8rem;
  height: 0.6rem;
`;

const Title = styled.span`
  font-size: 1.5rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  color: black;
  text-decoration: none;
  padding: 10px;
  margin-bottom: 20px;
`;

const PasswordVisibleBtn = styled(BackgroundImgDiv)<VisibleIconProps>`
  width: 100%;
  height: 100%;
  background-size: contain;
  background-image: url(${({ visible }) => (visible ? VisibleIcon : InVisibleIcon)});
  cursor: pointer;
  &:hover {
    background-image: url(${({ visible }) => (visible ? InVisibleIcon : VisibleIcon)});
  }
`;

export default {
  InputForm,
  Button,
  ErrorText,
  PasswordContainer,
  Input,
  InputContainer,
  InputEndText,
  Title,
  PasswordVisibleBtn,
};
