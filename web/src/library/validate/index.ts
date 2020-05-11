export const ID = 'id' as const;
export const PASSWORD = 'password' as const;
export const PASSWORD_CHECK = 'passwordCheck' as const;
export const NAME = 'name' as const;
export const SUB_EMAIL = 'subEmail' as const;

const LENGTH = {
  [ID]: {
    MIN: 5,
    MAX: 20,
  },
  [PASSWORD]: {
    MIN: 5,
    MAX: 30,
  },
  [NAME]: {
    MIN: 2,
    MAX: 10,
  },
  [SUB_EMAIL]: {
    MIN: 1,
    MAX: 50,
  },
};

const regexes = {
  [ID]: {
    regex: /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*$/,
    msg: '숫자, 영어, 마침표(.), 붙임표(-), 밑줄(_)만 사용할 수 있습니다',
  },
  [SUB_EMAIL]: {
    regex: /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}$/,
    msg: '올바르지 않은 이메일 형식입니다',
  },
  [NAME]: {
    regex: /^[a-zA-Z가-힣 ]{2,10}$/,
    msg: '영어, 완성된 한글만 사용할 수 있습니다',
  },
};

export const validate = ({ key, value }): string => {
  if (value === '') return '반드시 입력해 주세요';
  if (value[0] === ' ' || value[value.length - 1] === ' ') return '양옆 공백은 제거해 주세요';
  if (value.length < LENGTH[key].MIN) return `최소 ${LENGTH[key].MIN}자 이상 입력해 주세요`;
  if (value.length > LENGTH[key].MAX) return `최대 길이는 ${LENGTH[key].MAX}입니다.`;
  if (regexes[key] !== undefined) {
    const { regex, msg } = regexes[key];
    if (!regex.test(value)) {
      return msg;
    }
  }
  return '';
};

export const equalValidate = (val1, val2): string => {
  if (val1 !== val2) {
    return '같은 값을 입력해 주세요';
  }
  return '';
};
