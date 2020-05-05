import { registerRequest, registerSuccess, registerFailure } from './actions';

export type UserRegisterActionTypes =
  | ReturnType<typeof registerRequest>
  | ReturnType<typeof registerSuccess>
  | ReturnType<typeof registerFailure>;
