import { RegisterParam } from '~/@types/request/user';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseRegister } from '~/@types/response/user';
import { BusinessErrorResponse } from '~/@types/response/error';

export const REGISTER = 'REGISTER' as const;
export const REGISTER_REQUEST = 'REGISTER_REQUEST' as const;
export const REGISTER_SUCCESS = 'REGISTER_SUCCESS' as const;
export const REGISTER_FAILURE = 'REGISTER_FAILURE' as const;

export const registerRequest = (payload: RegisterParam) => ({
  type: REGISTER_REQUEST,
  payload: { data: payload },
});

export const registerSuccess = (payload: Promise<HTTPResponse<ResponseRegister>>) => ({
  type: REGISTER_SUCCESS,
  payload,
});

export const registerFailure = (payload: Promise<BusinessErrorResponse>) => ({
  type: REGISTER_FAILURE,
  payload,
});
