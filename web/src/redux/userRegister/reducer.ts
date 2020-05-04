import { UserActions, REGISTER_REQUEST, REGISTER_SUCCESS, REGISTER_FAILURE } from './actions';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseRegister } from '~/@types/response/user';
import { BusinessErrorResponse } from '~/@types/response/error';

interface UserRegisterApiState {
  loading: boolean;
  data: null | Promise<HTTPResponse<ResponseRegister>>;
  error: null | Promise<BusinessErrorResponse>;
}

const initialState = { loading: false, data: null, error: null };

const userRegisterReducer = (state = initialState, action: UserActions): UserRegisterApiState => {
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
