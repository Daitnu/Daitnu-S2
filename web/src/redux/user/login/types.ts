import { loginRequest, loginSuccess, loginFailure } from './actions';

export type UserLoginActionTypes =
  | ReturnType<typeof loginRequest>
  | ReturnType<typeof loginSuccess>
  | ReturnType<typeof loginFailure>;
