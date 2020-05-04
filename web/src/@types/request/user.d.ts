export interface RegisterParam {
  id: string;
  password: string;
  passwordCheck: string;
  name: string;
  subEmail: string;
}

export interface LoginParam {
  id: string;
  password: string;
}
