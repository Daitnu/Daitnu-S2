import { LoginParam } from '~/@types/request/user';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseLogin } from '~/@types/response/user';
import { BusinessErrorResponse } from '~/@types/response/error';

export const LOGIN_REQUEST = 'LOGIN_REQUEST' as const;
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS' as const;
export const LOGIN_FAILURE = 'LOGIN_FAILURE' as const;

export const loginRequest = (payload: LoginParam) => ({
  type: LOGIN_REQUEST,
  payload: { data: payload },
});

export const loginSuccess = (payload: Promise<HTTPResponse<ResponseLogin>>) => ({
  type: LOGIN_SUCCESS,
  payload,
});

export const loginFailure = (payload: Promise<BusinessErrorResponse>) => ({
  type: LOGIN_FAILURE,
  payload,
});
