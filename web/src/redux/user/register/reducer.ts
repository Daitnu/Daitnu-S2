import { REGISTER_REQUEST, REGISTER_SUCCESS, REGISTER_FAILURE } from './actions';
import { UserRegisterActionTypes } from './types';
import { ApiState } from '~/redux/util';
import { ResponseRegister } from '~/@types/response/user';

const initialState: ApiState<ResponseRegister> = { loading: false, data: null, error: null };

const userRegisterReducer = (
  state: ApiState<ResponseRegister> = initialState,
  action: UserRegisterActionTypes,
): ApiState<ResponseRegister> => {
  switch (action.type) {
    case REGISTER_REQUEST:
      return {
        ...initialState,
        loading: true,
      };
    case REGISTER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload,
        error: null,
      };
    case REGISTER_FAILURE:
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

export default userRegisterReducer;
