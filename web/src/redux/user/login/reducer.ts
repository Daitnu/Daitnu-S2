import { LOGIN_SUCCESS, LOGIN_REQUEST, LOGIN_FAILURE } from './actions';
import { UserLoginActionTypes } from './types';
import { ApiState } from '~/redux/util';
import { ResponseLogin } from '~/@types/response/user';

const initialState: ApiState<ResponseLogin> = { loading: false, data: null, error: null };

const userLoginReducer = (
  state: ApiState<ResponseLogin> = initialState,
  action: UserLoginActionTypes,
): ApiState<ResponseLogin> => {
  switch (action.type) {
    case LOGIN_REQUEST:
      return {
        ...initialState,
        loading: true,
      };
    case LOGIN_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload,
        error: null,
      };
    case LOGIN_FAILURE:
      return {
        ...state,
        loading: false,
        data: null,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default userLoginReducer;
