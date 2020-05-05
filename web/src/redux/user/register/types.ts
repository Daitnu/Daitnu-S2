import { HTTPResponse } from '~/@types/response/success';
import { ResponseRegister } from '~/@types/response/user';
import { BusinessErrorResponse } from '~/@types/response/error';
import { registerRequest, registerSuccess, registerFailure } from './actions';

export interface UserRegisterApiState {
  loading: boolean;
  data: null | Promise<HTTPResponse<ResponseRegister>>;
  error: null | Promise<BusinessErrorResponse>;
}

export type UserActionTypes =
  | ReturnType<typeof registerRequest>
  | ReturnType<typeof registerSuccess>
  | ReturnType<typeof registerFailure>;
