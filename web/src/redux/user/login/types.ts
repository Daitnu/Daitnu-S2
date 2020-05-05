import { HTTPResponse } from '~/@types/response/success';
import { ResponseLogin } from '~/@types/response/user';
import { BusinessErrorResponse } from '~/@types/response/error';
import { loginRequest, loginSuccess, loginFailure } from './actions';

export interface UserLoginApiState {
  loading: boolean;
  data: null | Promise<HTTPResponse<ResponseLogin>>;
  error: null | Promise<BusinessErrorResponse>;
}

export type UserLoginActionTypes =
  | ReturnType<typeof loginRequest>
  | ReturnType<typeof loginSuccess>
  | ReturnType<typeof loginFailure>;
