import { ValidateParam } from '~/@types/validate';

export const LENGTH = {
  ID: {
    MIN: 5,
    MAX: 20,
  },
  PASSWORD: {
    MIN: 5,
    MAX: 30,
  },
  NAME: {
    MIN: 2,
    MAX: 10,
  },
  EMAIL: {
    MAX: 50,
  },
};

export const validate = ({ val, min = 1, max }: ValidateParam): string => {
  if (val === '') return '반드시 입력해 주세요';
  if (val.includes(' ')) return '공백은 제거해 주세요';
  if (val.length < min) return `최소 ${min}자 이상 입력해 주세요`;
  if (val.length > max) return `최대 길이는 ${max}입니다.`;
  return '';
};

export const equalValidate = (val1, val2): string => {
  if (val1 !== val2) {
    return '같은 값을 입력해 주세요';
  }
  return '';
};
